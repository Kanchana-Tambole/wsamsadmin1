package com.ws.spring.exception;

import org.springframework.http.HttpStatus;

public enum ResponseCodes {

    USER_BARCODE_EXIST(1003, "FAILED", "", "Bar Code already Registered"), 
    USER_REGISTRATION(HttpStatus.CREATED.value(), "SUUCESS", "User Registration Completed.", ""),
    OTP_SENT_SUCCESS(1000, "SUCCESS", "Opt Sent to User registered mobile number.",""),
	PASSWORD_RESET(HttpStatus.ACCEPTED.value(), "SUCCESS", "Password Reset Success", ""),
	USER_ACTION_FAILED(1001, "FAILED","", "User Action Failed."),
	OTP_VALIDATION_FAILED(HttpStatus.FORBIDDEN.value(), "FAILED", "Otp Validation Failed",""),;

    private final int responseCode;
    private final String status;
    private final String message;
    private final String errorMessage;

    private ResponseCodes(int responseCode, String status, String message, String errorMessage) {
        this.responseCode = responseCode;
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


	
}
