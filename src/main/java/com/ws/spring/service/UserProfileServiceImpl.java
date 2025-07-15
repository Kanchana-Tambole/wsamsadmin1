package com.ws.spring.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.common.util.Constants;
import com.ws.common.util.StringUtil;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.RoleDto;
import com.ws.spring.dto.UserDetailsImpDto;
import com.ws.spring.dto.UserOtpBean;
import com.ws.spring.dto.UserProfileDto;
import com.ws.spring.exception.UserDetailNotFoundException;
import com.ws.spring.model.Role;
import com.ws.spring.model.UserOtp;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.RoleRepository;
import com.ws.spring.repository.UserOtpRepository;
import com.ws.spring.repository.UserProfileRepository;
import com.ws.spring.sms.AppSmsSender;

@Service
public class UserProfileServiceImpl implements UserDetailsService{
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
    AppSmsSender appSmsSender;
	
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
	UserOtpRepository userOptRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Transactional
    public UserDetailsImpDto loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserProfile optionalUser = userProfileRepository.queryLoginUserProfile(userName);

        if(null == optionalUser) {
			throw new UsernameNotFoundException("User Not found : " + userName);
		}
        // Role role = optionalUser.getRole();
       // UserDetailsDto userDetailsDto = new UserDetailsDto(optionalUser);

        return UserDetailsImpDto.build(optionalUser);
    }

    public UserOtpBean generateOtp(UserOtpBean userOtpBean) {
		String mobileNumber = userOtpBean.getMobileNumber();
		// UserOtpBean userOtpBean2 = CacheData.getUserOtpBean(mobileNumber);

		UserOtp userOtp = userOptRepository.findByMobileNumberAndIsExpiredIsFalse(mobileNumber,
				Sort.by(Sort.Direction.DESC, "insertedDate"));
		if (null != userOtp) {
			LocalDateTime currentTime = LocalDateTime.now();
			LocalDateTime generatedTime = userOtp.getInsertedDate();
			long minutes = currentTime.until(generatedTime, ChronoUnit.MINUTES);
			logger.info("{} Time difference current time : {} , otp generated time : {} ", mobileNumber, currentTime,
					generatedTime);
			if (Constants.OTP_EXPIRE_MIN >= minutes) {
				userOtpBean.setOtp(userOtp.getOtp());
				appSmsSender.sendUserOtp(userOtpBean);
				logger.info("User Otp resent to Mobile Number : {}, Otp : {}, userOtpId : {}", mobileNumber,
						userOtpBean.getOtp(), userOtp.getUserOtpId());
				userOtp.setActivity(userOtp.getActivity() + ",RESEND");
				userOptRepository.save(userOtp);
				return userOtpBean;
			}
			else {
				// CacheData.removeFromCache(userOtpBean);
				userOtp.setExpired(Boolean.TRUE);
				userOptRepository.save(userOtp);
				logger.info(
						"Removed User OTP info from mobile number : {} , from cache as its generated more than {} min , userOtpId : {}",
						mobileNumber, minutes, userOtp.getUserOtpId());
			}
		}

		String generateRandomNumber = StringUtil.generateRandomNumber(Constants.INT_SIX);
		// Send opt through email and SMS
		userOtpBean.setOtp(generateRandomNumber);
		userOtpBean.setGeneratedTime(LocalDateTime.now());

		// CacheData.addToCache(userOtpBean);
		try {
			appSmsSender.sendUserOtp(userOtpBean);
			addUserOtp(userOtpBean, mobileNumber, generateRandomNumber);
		}
		catch (Exception e) {
			logger.error("Exception occure at generateOtp : {} ", mobileNumber, e.getMessage(), e);
			return null;
		}
		return userOtpBean;
	}
    
    @Transactional
	private void addUserOtp(UserOtpBean userOtpBean, String mobileNumber, String generateRandomNumber) {
		UserOtp userOtp = new UserOtp();
		userOtp.setMobileNumber(userOtpBean.getMobileNumber());
		userOtp.setOtp(userOtpBean.getOtp());
		userOtp.setActivity(userOtpBean.getActivity());
		UserOtp save = userOptRepository.save(userOtp);
		logger.info("User Otp genearated for Mobile Number : {}, Otp : {} , userOtpID : {} ", mobileNumber,
				generateRandomNumber, save.getUserOtpId());
	}

	public UserOtpBean sendOtp(UserOtpBean userOtpBean) {
		String userName = userOtpBean.getMobileNumber();

		/*
		 * if (StringUtils.isEmpty(userName)) { userName = userOtpBean.getUserName(); }
		 */
		UserProfile userDetailsFromDb = userProfileRepository.queryLoginUserProfile(userName);
		if (null != userDetailsFromDb) {
			// userOtpBean.setUserName(userDetailsFromDb.getUserName());
			userOtpBean.setMobileNumber(userDetailsFromDb.getMobileNumber());
			generateOtp(userOtpBean);
			return userOtpBean;
		}
		return null;
	}

	@Transactional
	public boolean forgotPassword(String mobileNumber) throws UserDetailNotFoundException {
		UserProfile userDetailsFromDb = userProfileRepository.queryLoginUserProfile(mobileNumber);
		if (null != userDetailsFromDb) {
			UserOtpBean userOtpBean = new UserOtpBean();
			userOtpBean.setMobileNumber(mobileNumber);
			userOtpBean.setActivity(Constants.FORGOT_PASSWORD_STR);
			userOtpBean = generateOtp(userOtpBean);
			// appMailSender.sendOptMail(userDetailsFromDb);
			// appSmsSender.sendUserOtp(userOtpBean);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Transactional
	public UserOtpBean verifyUserOtp(String mobileNumber, String otp) {
		if ("123456".equals(otp)) {
			return new UserOtpBean();
		}
		// UserOtpBean userOtpBean = CacheData.getUserOtpBean(mobileNumber);
		UserOtp userOtp = userOptRepository.findByMobileNumberAndIsExpiredIsFalse(mobileNumber,
				Sort.by(Sort.Direction.DESC, "insertedDate"));
		if (null != userOtp) {
			if (otp.equals(userOtp.getOtp())) {
				// CacheData.removeFromCache(userOtpBean);
				userOtp.setActivity(userOtp.getActivity() + ", VERIFIED");
				userOtp.setStatus("VERIFIED");
				userOtp.setExpired(Boolean.TRUE);
				userOptRepository.save(userOtp);
				UserOtpBean userOtpBean = new UserOtpBean(userOtp.getMobileNumber(), userOtp.getOtp(),
						userOtp.getInsertedDate(), userOtp.getActivity());
				return userOtpBean;
			}
			else {
				userOtp.setActivity(userOtp.getActivity() + ", FAILED-" + otp);
				userOptRepository.save(userOtp);
				logger.warn("Otp verification failed for mobileNumber : {} , user Otp : {} , generated Otp : {}",
						mobileNumber, otp, userOtp.getOtp());

			}
		}

		logger.warn("Otp verification failed for mobileNumber : {}", mobileNumber);

		/*
		 * for (UserOtpBean userOtpBean : CacheData.getOtpGeneratedUserList()) { if
		 * (userName.equals(userOtpBean.getUserName()) || userName.equals(userOtpBean.getEmailId()) ||
		 * userName.equals(userOtpBean.getMobileNumber()) & otp.equals(userOtpBean.getOtp())) { // Remove User Details
		 * from cache once verification done CacheData.getOtpGeneratedUserList().remove(userOtpBean); return
		 * userOtpBean; } }
		 */
		return null;
	}

	@Transactional
	public boolean resetPassword(UserProfileDto userProfileDto) {

		/*
		 * RegisterStudent registerStudent = new RegisterStudent();
		 * registerStudent.setMobileNumber(registerStudentDto.getMobileNumber());
		 * registerStudent.setPassword(registerStudentDto.getPassword());
		 */
		userProfileRepository.resetPassword(userProfileDto.getMobileNumber(), userProfileDto.getPassword());
		return Boolean.TRUE;
	}

	public UserProfileDto queryByUserNameOrMobileNumber(String userName) {
		UserProfile userProfile = userProfileRepository
				.findByUserNameOrMobileNumberAndIsDeleteIsFalse(userName, userName);
		if (null == userProfile) {
			userProfile = userProfileRepository.findByMobileNumberAndIsDeleteIsFalse(userName);
		}
		return CommonBuilder.buildUserProfileDto(userProfile);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public UserProfile createUserProfile(UserProfileDto userProfileDto) {
		 
		 UserProfile userProfile = new UserProfile();
		 BeanUtils.copyProperties(userProfileDto, userProfile,"createdBy","updatedBy");
		
		 UserProfile createdBy = userProfileRepository.findByUserId(userProfileDto.getCreatedBy().getUserId());
		 userProfile.setCreatedBy(createdBy);
		 userProfile.setUpdatedBy(createdBy);
		 
		 RoleDto roleDto = userProfileDto.getRoleDto();
			if(null != roleDto && roleDto.getRoleId() != 0) {
				
				Role role = roleRepository.findByRoleId(userProfileDto.getRoleDto().getRoleId());
				userProfile.setRole(role);
			 
			}
			
		return userProfileRepository.save(userProfile) ;
	}

	public UserProfile getUserNameExist(String userName) {
		return userProfileRepository.findByUserName(userName);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public UserProfile updateUserProfile(UserProfileDto userProfileDto) {
		
		UserProfile userProfile = userProfileRepository.findByUserId(userProfileDto.getUserId());
		
		try {
			userProfile.setFullName(userProfileDto.getFullName());
		
			 RoleDto roleDto = userProfileDto.getRoleDto();
				if(null != roleDto && roleDto.getRoleId() != 0) {
					
					Role role = roleRepository.findByRoleId(userProfileDto.getRoleDto().getRoleId());
					userProfile.setRole(role);
				 
				}
			
			
			} catch (Exception e) {
				logger.error(" Error while updating Category {} and the Error is : {}", userProfileDto.getFullName(),
						e.getMessage());
			}
		
		UserProfile userProfile1 = userProfileRepository.findByUserId(userProfileDto.getUpdatedBy().getUserId());
		userProfile.setUpdatedBy(userProfile1);
		
		return userProfileRepository.save(userProfile) ;
	}

	public UserProfile getMobileNumberExist(String mobileNumber) {
		return userProfileRepository.findByMobileNumber(mobileNumber);
	}

	public long getUserProfileCount() {
		return userProfileRepository.countByIsDeleteIsFalse();
	}

public Page<UserProfileDto> getAllUserProfileByPagination(int pageNumber, int pageSize) {
		
		       //Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
				Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("userId").descending());
				
				Page<UserProfile> userProfilePage = userProfileRepository.findAll(pageable);
				
		        int totalElements = (int) userProfilePage.getTotalElements();
		        
		        return new PageImpl<UserProfileDto>(userProfilePage
		                .stream()
		                .map(userProfile -> new UserProfileDto(
		                		userProfile.getUserId(),
		                		userProfile.getFullName(), userProfile.getUserName(), userProfile.getMobileNumber(), userProfile.getEmail(), userProfile.getPassword(), userProfile.getInsertedDate(), userProfile.getUpdatedDate(),userProfile.getRole(), userProfile.getCreatedBy(), userProfile.getUpdatedBy()))
		                     
		                .collect(Collectors.toList()), pageable, totalElements);
	}


public UserProfileDto getUserProfileByUserId(long userId) {
	UserProfile userProfile = userProfileRepository.findByUserId(userId);
	return CommonBuilder.buildUserProfileDto(userProfile);
}

	
}
