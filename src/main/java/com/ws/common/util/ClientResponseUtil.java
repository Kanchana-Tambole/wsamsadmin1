package com.ws.common.util;

import org.springframework.http.HttpStatus;

import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.exception.ResponseCodes;
import com.ws.spring.model.UserProfile;

public class ClientResponseUtil {

	public static ClientResponseBean userRegistrationSuccess(UserProfile userProfile) {

		return new ClientResponseBean(HttpStatus.CREATED.value(), "SUUCESS", "User Registration Completed.", "");
	}

	public static ClientResponseBean UserRegistrationFailed() {
		return new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", "" ,"User Registration Failed.");
	}

	public static ClientResponseBean getUserOptValidationSuccess() {
		return new ClientResponseBean(1000, "SUCCESS", "User Opt validation success.", "");
	}

	public static ClientResponseBean getUserOptValidationFailed() {
		return new ClientResponseBean(1001, "FAILED", "", "User Opt validation failed.");
	}

	public static ClientResponseBean sentOptSucces() {
		return new ClientResponseBean(1000, "SUCCESS", "Opt Sent to User registered mobile number.", "");
	}

	public static ClientResponseBean sentOptFailed() {
		return new ClientResponseBean(1001, "FAILED", "", "Failed to Send Otp to User registered mobile number.");
	}

	public static ClientResponseBean userLoginSuccess() {
        return new ClientResponseBean(1000, "SUCCESS", "User Login success.", "");
    }

    public static ClientResponseBean userLoginFailed() {
        return new ClientResponseBean(10001, "FAILED", "", "User Login Failed.");
    }

    public static ClientResponseBean getSuccessResponse() {
        return new ClientResponseBean(1000, "SUCCESS", "Activity success.", "");
    }

    public static ClientResponseBean getErrorResponse() {
        return new ClientResponseBean(10001, "FAILED", "", "Activity Failed.");
    }
	public static ClientResponseBean getResponse(ResponseCodes responseCode) {
		return new ClientResponseBean(responseCode.getResponseCode(), responseCode.getStatus(),
				responseCode.getMessage(), "");
	}

	public static ClientResponseBean getExceptionResponse(int responseCode, String message) {
		return new ClientResponseBean(responseCode, "FAILED","", message);
	}

	public static ClientResponseBean getUserDetailNotFoundResponse(String userName) {
		return new ClientResponseBean(1005, "FAILED", "",userName + " - User Details not found ");
	}

}
