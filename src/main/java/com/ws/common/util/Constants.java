package com.ws.common.util;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface Constants {

	/**
	 * TIme zone used for the application
	 */
	String TIME_ZONE_ID = "Asia/Kolkata";

	/**
	 * User Role description
	 */
	long ROLE_ID_SUPERADMIN = 1;

	long ROLE_ID_ADMIN = 2;

	long ROLE_ID_COLLEGEADMIN = 3;

	long ROLE_ID_REPORTER = 4;

	long ROLE_ID_UESR = 5;

	long ROLE_ID_STUDENT = 6;

	String ROLE_SUPERADMIN = "SuperAdmin";

	String ROLE_ADMIN = "Admin";

	String ROLE_COLLEGE_ADMIN = "CollgeAdmin";

	String ROLE_REPOTER = "Reporter";

	String ROLE_USER = "User";

	String ROLE_STUDENT = "Student";

	String COURSE_NAME_RESEARCH = "Research";

	String COURSE_NAME_GENERAL_APTITUDE = "General Aptitude";

	/**
	 * isActive parameter descriptions
	 */
	int REGISTERED = 0;

	int ACTIVE = 1;

	int REJECTED = 2;

	int BLOCKED = 3;

	int INT_ONE = 1;

	/** The Constant BLANK. */
	String EMPTY_STR = "";

	/** The Constant COLON. */
	String COLON = " : ";

	/** The Constant DASH. */
	String DASH = " - ";

	/** The Constant KEY_TXNID. */
	String KEY_TXNID = "TXNID";

	/** The Constant XPATH_TXNID. */
	String XPATH_TXNID = "//transcationId";

	/** The Constant ENTRY. */
	String ENTRY = "Entry";

	/** The Constant EXIT. */
	String EXIT = "Exit";

	/** COnstant int numbers */
	int INT_SIX = 6;

	String REGISTRATION_STR = "REGISTRATION";

	String FORGOT_PASSWORD_STR = "FORGOT_PASSWORD";

	String DEVICE_UPDATE_STR = "DEVICE_UPDATE";

	String LOGIN_BY_MPIN = "LOGIN_BY_MPIN";

	String LOGIN_BY_OTP = "LOGIN_BY_OTP";

	String LOGIN_BY_PASSWORD = "LOGIN_BY_PASSWORD";

	/**
	 * sms message code and content
	 */

	long ONE_SECOND = 1000;

	long ONE_MIN = 60 * ONE_SECOND;

	long ONE_HOUR = 60 * ONE_MIN;

	// Topic type is PDF
	int TOPIC_TYPE_PDF = 0;

	// Topic type is DOC
	int TOPIC_TYPE_DOC = 1;

	// Topic type is CSV
	int TOPIC_TYPE_CSV = 2;

	int TOPIC_TYPE_XLS = 3;

	long STATUS_DRAFT = 0;

	long STATUS_ACTIVE = 1;

	long ACCESS_LEVEL_PRIVATE = 0;

	long ACCESS_LEVEL_PUBLIC = 1;

	// 1000L*60L*60L - 1 hour
	long HOUR = 3600000L;

	long MINUTE = 60000L;

	long OTP_EXPIRE_MIN = 15;

	// Spring profiles for development, test and production, see https://www.jhipster.tech/profiles/
	/** Constant <code>SPRING_PROFILE_DEVELOPMENT="dev"</code> */
	String SPRING_PROFILE_DEVELOPMENT = "local";

	/** Constant <code>SPRING_PROFILE_TEST="test"</code> */
	String SPRING_PROFILE_TEST = "test";

	/** Constant <code>SPRING_PROFILE_PRODUCTION="prod"</code> */
	String SPRING_PROFILE_PRODUCTION = "prod";

	/**
	 * Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry) Constant
	 * <code>SPRING_PROFILE_CLOUD="cloud"</code>
	 */
	String SPRING_PROFILE_CLOUD = "cloud";

	/**
	 * Spring profile used when deploying to Heroku Constant <code>SPRING_PROFILE_HEROKU="heroku"</code>
	 */
	String SPRING_PROFILE_HEROKU = "heroku";

	/**
	 * Spring profile used when deploying to Amazon ECS Constant <code>SPRING_PROFILE_AWS_ECS="aws-ecs"</code>
	 */
	String SPRING_PROFILE_AWS_ECS = "aws-ecs";

	/**
	 * Spring profile used to enable swagger Constant <code>SPRING_PROFILE_SWAGGER="swagger"</code>
	 */
	String SPRING_PROFILE_SWAGGER = "swagger";

	/**
	 * Spring profile used to disable running liquibase Constant <code>SPRING_PROFILE_NO_LIQUIBASE="no-liquibase"</code>
	 */
	String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

	/**
	 * Spring profile used when deploying to Kubernetes and OpenShift Constant <code>SPRING_PROFILE_K8S="k8s"</code>
	 */
	String SPRING_PROFILE_K8S = "k8s";

	String FILE_ACTION_VIEW = "view";

	String FILE_ACTION_DOWNLOAD = "download";

	String PAYMENT_SUCCESS = "Processed";

	String PAYMENT_REFERRAL = "Referral";

	String PAYMENT_INITIATD = "Initiated";

	String PAYMENT_FIELED = "Failed";

	long REFERRAL_POINTS_DEFAULT = 3;

	String APP_CONFIG_CREDIT_REFERRAL_POINTS = "CREDIT_REFERRAL_POINTS";

	String FRIEND_PRO_PUSHNOTIFICATION_TITLE = "FriendPro Notification";

	String FRIEND_PRO_PUSHNOTIFICATION_TOPIC = "FriendPro Notification";

	List<String> BLOCKLISTED_USER = ImmutableList.of("6282177621", "6282617688", "7483600702", "7907957996",
			"9400618778", "9496699507", "9606296007");
}
