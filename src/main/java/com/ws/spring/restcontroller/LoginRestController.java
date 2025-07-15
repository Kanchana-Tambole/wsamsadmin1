package com.ws.spring.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ws.common.util.ClientResponseUtil;
import com.ws.common.util.Constants;
import com.ws.spring.dto.UserDetailsImpDto;
import com.ws.spring.dto.UserOtpBean;
import com.ws.spring.dto.UserProfileDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.exception.ResponseCodes;
import com.ws.spring.exception.UserDetailNotFoundException;
import com.ws.spring.security.jwt.JwtProvider;
import com.ws.spring.security.jwt.JwtRequest;
import com.ws.spring.security.jwt.JwtResponse;
import com.ws.spring.service.UserProfileServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/login")
@Api(value = "Login Management System", tags = "Login Management System")
public class LoginRestController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    UserProfileServiceImpl userProfileService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;
 
	
	@GetMapping("/v1/index")
    public String index() {
        logger.info("Loading index page");
        return "index";
    }

    @PostMapping("/v1/userLogin")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest loginRequest) {
		String jwt = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			jwt = jwtProvider.generateJwtToken(authentication);
			UserDetailsImpDto userDetailsImpDto = (UserDetailsImpDto) authentication.getPrincipal();

			return ResponseEntity
					.ok(new JwtResponse(jwt,userDetailsImpDto.getUserId(),userDetailsImpDto.getUserName(),userDetailsImpDto.getMobileNumber()));
		} catch (Exception e) {
			logger.error("Exception occured  : {} ", e.getMessage(), e);

			return ResponseEntity.ok()
					.body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
    
    
  //  @SuppressWarnings("deprecation")
	@ApiOperation(value = "Generate Otp for validation", response = ClientResponseBean.class)
	@PostMapping("/v1/generateOtp")
	public ResponseEntity<?> generateOtp(@RequestBody UserOtpBean userOtpBean) {
		try {
			String userName = userOtpBean.getMobileNumber();
			/*
			 * if (StringUtils.isEmpty(userName)) { userName = userOptBean.getUserName(); }
			 */
			logger.info("generate Otp for userName : {}", userName);
			UserOtpBean userOptBeanReturn = null;
			if (!StringUtils.isEmpty(userOtpBean.getActivity())
					&& Constants.REGISTRATION_STR.equals(userOtpBean.getActivity())) {
				userOptBeanReturn = userProfileService.generateOtp(userOtpBean);
			}
			else {
				userOptBeanReturn = userProfileService.sendOtp(userOtpBean);
			}
			if (null != userOptBeanReturn) {
				return ResponseEntity.ok().body(ClientResponseUtil.getResponse(ResponseCodes.OTP_SENT_SUCCESS));
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found",
					new UserDetailNotFoundException("User Not Found"));
		}
		catch (Exception ex) {
			logger.error("Exception Occure : {} ", ex.getMessage(), ex);
			return ResponseEntity.ok()
					.body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
		}
	}
    
    @GetMapping("/v1/forgotPassword/{mobileNumber}")
	public ResponseEntity<ClientResponseBean> forgotPassword(@PathVariable("mobileNumber") String mobileNumber) {
		logger.info("forgotPassword for username : {}", mobileNumber);
		try {
			if (userProfileService.forgotPassword(mobileNumber)) {
				return new ResponseEntity<>(ClientResponseUtil.sentOptSucces(), HttpStatus.OK);
			}
			logger.warn("forgotPassword : User Details not found for username : {}", mobileNumber);
			return new ResponseEntity<>(ClientResponseUtil.getUserDetailNotFoundResponse(mobileNumber),
					HttpStatus.NOT_FOUND);
		}
		catch (Exception ex) {
			logger.error("Exception Occure : {} ", ex.getMessage(), ex);
			return ResponseEntity.ok()
					.body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
		}
	}
    
    
    @PostMapping("/v1/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody UserProfileDto userProfileDto) {
		String mobileNumber = userProfileDto.getMobileNumber();
		logger.info("resetPassword for mobileNumber : {}", mobileNumber);
		try {
			if (null == userProfileService.verifyUserOtp(userProfileDto.getMobileNumber(),
					userProfileDto.getOtp())) {
				return ResponseEntity.ok().body(ClientResponseUtil.getResponse(ResponseCodes.OTP_VALIDATION_FAILED));
			}
			userProfileDto.setPassword(passwordEncoder.encode(userProfileDto.getNewPassword()));
			if (userProfileService.resetPassword(userProfileDto)) {
				return ResponseEntity.ok().body(ClientResponseUtil.getResponse(ResponseCodes.PASSWORD_RESET));
			}
			else {
				return ResponseEntity.ok().body(ClientResponseUtil.getResponse(ResponseCodes.USER_ACTION_FAILED));
			}
		}
		catch (Exception ex) {
			logger.error("Exception Occure : {} ", ex.getMessage(), ex);
			return ResponseEntity.ok()
					.body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
		}
	}

    @ApiOperation(value = "Resend Otp for validation", response = ClientResponseBean.class)
	@PostMapping("/v1/resendOtp")
	public ResponseEntity<?> resendOtp(@RequestBody UserOtpBean userOptBean) {
		try {
			String mobileNumber = userOptBean.getMobileNumber();
			/*
			 * if (StringUtils.isEmpty(mobileNumber)) { mobileNumber = userOptBean.getUserName(); }
			 */
			logger.info("resend Otp for mobileNumber : {}", mobileNumber);
			UserOtpBean userOptBeanReturn = userProfileService.generateOtp(userOptBean);

			if (null != userOptBeanReturn) {
				return ResponseEntity.ok().body(ClientResponseUtil.sentOptSucces());
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found",
					new UserDetailNotFoundException("User Not Found"));
		}
		catch (Exception ex) {
			logger.error("Exception Occure : {} ", ex.getMessage(), ex);
			return ResponseEntity.ok()
					.body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
		}
	}
    
    @GetMapping("/v1/queryUserProfileByUserName/{userName}")
	ResponseEntity<?> queryUserProfileByUserName(@PathVariable("userName") String userName) {
		try {
			UserProfileDto userProfileDto = userProfileService.queryByUserNameOrMobileNumber(userName);
			if (null == userProfileDto) {
				return new ResponseEntity<>(ClientResponseUtil.getUserDetailNotFoundResponse(userName),
						HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok().body(userProfileDto);
		}
		catch (Exception e) {
			return new ResponseEntity<>(ClientResponseUtil.getUserDetailNotFoundResponse(userName),
					HttpStatus.NOT_FOUND);
		}

	}
    
    
    
    
    
    
}
