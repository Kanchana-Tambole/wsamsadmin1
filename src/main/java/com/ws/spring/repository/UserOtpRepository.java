package com.ws.spring.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.UserOtp;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {

	UserOtp findByMobileNumberAndIsExpiredIsFalse(String mobileNumber, Sort sort);

}
