package com.ws.spring.sms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ws.common.util.StringUtil;
import com.ws.spring.dto.UserOtpBean;
import com.ws.spring.model.UserProfile;

@Service
@PropertySource("classpath:smsconfig.properties")
public class AppSmsSender {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Environment env;

    @Autowired
    SendSms sendSms;

    // private String smsMainUrl =
    // "http://smshorizon.co.in/api/sendsms.php?user={{username}}&apikey={{apikey}}&message={{message}}&mobile={{mobile}}&senderid={{senderid}}&type={{type}}";
    private String smsMainUrl;

    public AppSmsSender() {

    }

    private void initilizeSmsUrl() {
        Map<String, String> mainReplacements = new HashMap<String, String>();
        mainReplacements.put("username", env.getProperty("slapp.sms.username"));
        mainReplacements.put("apikey", env.getProperty("slapp.sms.apikey"));
        mainReplacements.put("senderid", env.getProperty("slapp.sms.senderid"));
        mainReplacements.put("type", env.getProperty("slapp.sms.type"));

        smsMainUrl = StringUtil.messageFormat(env.getProperty("slapp.sms.mainUrl"), mainReplacements);
    }

    public void sendUserRegistrationSms(UserProfile userDetails) {
        String mainUrl = prepareSms(userDetails, "slappp.sms.user.registration");
        sendSms.sendSmstoUser(mainUrl, userDetails.getMobileNumber());
    }

    public void sendUserOtp(UserOtpBean userOtpBean) {

        if (StringUtil.checkNullOrEmpty(smsMainUrl)) {
            initilizeSmsUrl();
        }

        Map<String, String> replacements = new HashMap<String, String>();
        // replacements.put("user", userOtpBean.getUserName());
        // replacements.put("username", userOtpBean.getUserName());
        replacements.put("otp", userOtpBean.getOtp());
        String otpMessage = env.getProperty("slapp.sms.user.otp");
        String message = StringUtil.messageFormat(otpMessage, replacements);

        Map<String, String> mainUrlreplacements = new HashMap<String, String>();
        String messageEncoded = StringUtil.encode(message);
        logger.info("Message : {} , String util Encoder {}", message, messageEncoded);
        mainUrlreplacements.put("message", messageEncoded);
        String mobileNumber = userOtpBean.getMobileNumber();
        mainUrlreplacements.put("mobile", mobileNumber);
        String mainUrl = StringUtil.messageFormat(smsMainUrl, mainUrlreplacements);
        sendSms.sendSmstoUser(mainUrl, userOtpBean.getMobileNumber());
    }

    private String prepareSms(UserProfile userDetails, String msgCode) {
        if (StringUtil.checkNullOrEmpty(smsMainUrl)) {
            initilizeSmsUrl();
        }
        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("fullname", userDetails.getFullName());
        replacements.put("user", userDetails.getUserName());
        replacements.put("password", userDetails.getPassword());
        replacements.put("username", userDetails.getUserName());
        replacements.put("otp", userDetails.getOtp());
        String message = StringUtil.messageFormat(env.getProperty(msgCode), replacements);

        Map<String, String> mainUrlreplacements = new HashMap<String, String>();
        String messageEncoded = StringUtil.encode(message);
        logger.info("Message : {} , String util Encoder {}", message, messageEncoded);

        mainUrlreplacements.put("message", messageEncoded);
        String mobileNumber = userDetails.getMobileNumber();
        mainUrlreplacements.put("mobile", mobileNumber);

        return StringUtil.messageFormat(smsMainUrl, mainUrlreplacements);
    }

    private String enrolleredStudentSms(String mobileNumber, String msgCode) {
        if (StringUtil.checkNullOrEmpty(smsMainUrl)) {
            initilizeSmsUrl();
        }
        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("mobile", mobileNumber);
        String message = StringUtil.messageFormat(env.getProperty(msgCode), replacements);

        Map<String, String> mainUrlreplacements = new HashMap<String, String>();
        String messageEncoded = StringUtil.encode(message);
        logger.info("Message : {} , String util Encoder {}", message, messageEncoded);

        mainUrlreplacements.put("message", messageEncoded);
        mainUrlreplacements.put("mobile", mobileNumber);

        return StringUtil.messageFormat(smsMainUrl, mainUrlreplacements);
    }

    public void sendSms(String mobileNumber, String msgCode) {
        sendSms.sendSmstoUser(enrolleredStudentSms(mobileNumber, msgCode), mobileNumber);
    }
    
    public String getProperty(String key) {
		return env.getProperty(key);
	}

}
