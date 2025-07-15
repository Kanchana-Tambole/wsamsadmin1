package com.ws.spring.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

	UserProfile findByUserId(long userId);

	UserProfile findUserByUserName(String userName);

	@Query("SELECT u FROM UserProfile u WHERE u.userName = :userName or u.mobileNumber = :userName")
	UserProfile findByUserName(@Param("userName")String userName);

	@Query("SELECT u FROM UserProfile u WHERE u.userName = :userName or u.mobileNumber = :userName")
    UserProfile queryLoginUserProfile(@Param("userName") String userName);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update UserProfile set password = :password where mobileNumber = :mobileNumber")
	int resetPassword(@Param("mobileNumber") String mobileNumber, @Param("password") String password);

	List<UserProfile> findByIsDeleteIsFalse(Sort sort);

	long countByIsDeleteIsFalse();

	List<UserProfile> findByIsDeleteIsTrue(Sort sort);

	UserProfile findByMobileNumber(String mobileNumber);

	UserProfile findUserByUserNameAndIsDeleteIsTrue(String userName);

	Object findByMobileNumberAndIsDeleteIsTrue(String mobileNumber);

	UserProfile findByUserNameOrMobileNumberAndIsDeleteIsFalse(String userName, String userName2);

	UserProfile findByMobileNumberAndIsDeleteIsFalse(String userName);

	

}
