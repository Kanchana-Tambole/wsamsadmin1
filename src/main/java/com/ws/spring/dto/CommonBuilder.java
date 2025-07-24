package com.ws.spring.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.util.CollectionUtils;

import com.ws.spring.model.AcademicCategory;
import com.ws.spring.model.AcademicCourse;
import com.ws.spring.model.AcademicModule;
import com.ws.spring.model.AcademicTopic;
import com.ws.spring.model.AcademicYear;
import com.ws.spring.model.Advertisement;
<<<<<<< HEAD
import com.ws.spring.model.Batch;
import com.ws.spring.model.BloodGroup;
import com.ws.spring.model.Caste;
=======
import com.ws.spring.model.BankType;
import com.ws.spring.model.Batch;
import com.ws.spring.model.BloodGroup;
import com.ws.spring.model.BoardUniversity;
import com.ws.spring.model.Caste;
import com.ws.spring.model.CertificateType;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.model.City;
import com.ws.spring.model.Country;
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.model.Department;
<<<<<<< HEAD
import com.ws.spring.model.DocumentType;
import com.ws.spring.model.FacultyProfile;
=======
import com.ws.spring.model.DisabilityType;
import com.ws.spring.model.DocumentType;
import com.ws.spring.model.EducationLevel;
import com.ws.spring.model.EntranceTestSchedule;
import com.ws.spring.model.FacultyProfile;
import com.ws.spring.model.FeesCategory;
import com.ws.spring.model.FeesType;
import com.ws.spring.model.GradingScheme;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.model.JobDesignation;
import com.ws.spring.model.Language;
import com.ws.spring.model.Location;
import com.ws.spring.model.News;
import com.ws.spring.model.Occupation;
<<<<<<< HEAD
import com.ws.spring.model.Promo;
import com.ws.spring.model.Qualification;
import com.ws.spring.model.Religion;
import com.ws.spring.model.Skill;
import com.ws.spring.model.State;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.model.SuccessStory;
=======
import com.ws.spring.model.PaymentMode;
import com.ws.spring.model.Promo;
import com.ws.spring.model.Qualification;
import com.ws.spring.model.Religion;
import com.ws.spring.model.ScholarshipScheme;
import com.ws.spring.model.Skill;
import com.ws.spring.model.State;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.model.Subject;
import com.ws.spring.model.SuccessStory;
import com.ws.spring.model.TestCenter;
import com.ws.spring.model.TestType;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.model.UpskillCategory;
import com.ws.spring.model.UpskillCourse;
import com.ws.spring.model.UpskillModule;
import com.ws.spring.model.UpskillTopic;
import com.ws.spring.model.UserProfile;

public class CommonBuilder {
	
<<<<<<< HEAD
=======
    public static TestCenterDto buildTestCenterDto(TestCenter center) {
        if (center == null) {
            return null;
        }

        return TestCenterDto.builder()
                .id(center.getId())
                .centerName(center.getCenterName())
                .city(center.getCity())
                .address(center.getAddress())
                .contactNumber(center.getContactNumber())
                .isActive(center.getIsActive())
                .createdAt(center.getCreatedAt())
                .updatedAt(center.getUpdatedAt())
                .createdBy(center.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                center.getCreatedBy().getUserId(),
                                center.getCreatedBy().getFullName(),
                                center.getCreatedBy().getUserName(),
                                center.getCreatedBy().getMobileNumber()))
                .updatedBy(center.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                center.getUpdatedBy().getUserId(),
                                center.getUpdatedBy().getFullName(),
                                center.getUpdatedBy().getUserName(),
                                center.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static TestCenterDtoList buildTestCenterDataDto(TestCenter center) {
        if (center == null) {
            return null;
        }

        return TestCenterDtoList.builder()
                .id(center.getId())
                .centerName(center.getCenterName())
                .build();
    }

    public static List<TestCenterDto> buildTestCenterFullDtoList(List<TestCenter> centers) {
        if (CollectionUtils.isEmpty(centers)) {
            return null;
        }

        return centers.stream()
                .map(CommonBuilder::buildTestCenterDto)
                .collect(Collectors.toList());
    }

    public static List<TestCenterDtoList> buildTestCenterDtoList(List<TestCenter> centers) {
        if (CollectionUtils.isEmpty(centers)) {
            return null;
        }

        return centers.stream()
                .map(CommonBuilder::buildTestCenterDataDto)
                .collect(Collectors.toList());
    }
	
	
    public static EntranceTestScheduleDto buildEntranceTestScheduleDto(EntranceTestSchedule schedule) {

        if (schedule == null) {
            return null;
        }

        return EntranceTestScheduleDto.builder()
                .id(schedule.getId())
                .testDate(schedule.getTestDate())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .remarks(schedule.getRemarks())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .testType(schedule.getTestType() == null ? null
                        : new TestTypeDtoList(schedule.getTestType().getId(), schedule.getTestType().getTestName()))
                .testCenter(schedule.getTestCenter() == null ? null
                        : new TestCenterDtoList(schedule.getTestCenter().getId(), schedule.getTestCenter().getCenterName()))
                .createdBy(schedule.getCreatedBy() == null ? null
                        : new UserProfileDtoList(
                                schedule.getCreatedBy().getUserId(),
                                schedule.getCreatedBy().getFullName(),
                                schedule.getCreatedBy().getUserName(),
                                schedule.getCreatedBy().getMobileNumber()))
                .updatedBy(schedule.getUpdatedBy() == null ? null
                        : new UserProfileDtoList(
                                schedule.getUpdatedBy().getUserId(),
                                schedule.getUpdatedBy().getFullName(),
                                schedule.getUpdatedBy().getUserName(),
                                schedule.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static EntranceTestScheduleDtoList buildEntranceTestScheduleDataDto(EntranceTestSchedule schedule) {

        if (schedule == null) {
            return null;
        }

        String testTypeName = schedule.getTestType() != null ? schedule.getTestType().getTestName() : null;
        String testCenterName = schedule.getTestCenter() != null ? schedule.getTestCenter().getCenterName() : null;

        return EntranceTestScheduleDtoList.builder()
                .id(schedule.getId())
                .testTypeName(testTypeName)
                .testCenterName(testCenterName)
                .build();
    }

    public static List<EntranceTestScheduleDto> buildEntranceTestScheduleFullDtoList(List<EntranceTestSchedule> scheduleList) {
        if (CollectionUtils.isEmpty(scheduleList)) {
            return null;
        }

        return scheduleList.stream()
                .map(CommonBuilder::buildEntranceTestScheduleDto)
                .collect(Collectors.toList());
    }

    public static List<EntranceTestScheduleDtoList> buildEntranceTestScheduleMinimalDtoList(List<EntranceTestSchedule> scheduleList) {
        if (CollectionUtils.isEmpty(scheduleList)) {
            return null;
        }

        return scheduleList.stream()
                .map(CommonBuilder::buildEntranceTestScheduleDataDto)
                .collect(Collectors.toList());
    }

	
	
    public static TestTypeDto buildTestTypeDto(TestType testType) {
        if (testType == null) {
            return null;
        }

        return TestTypeDto.builder()
                .id(testType.getId())
                .testName(testType.getTestName())
                .description(testType.getDescription())
                .isActive(testType.getIsActive())
                .createdAt(testType.getCreatedAt())
                .updatedAt(testType.getUpdatedAt())
                .createdBy(testType.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                testType.getCreatedBy().getUserId(),
                                testType.getCreatedBy().getFullName(),
                                testType.getCreatedBy().getUserName(),
                                testType.getCreatedBy().getMobileNumber()))
                .updatedBy(testType.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                testType.getUpdatedBy().getUserId(),
                                testType.getUpdatedBy().getFullName(),
                                testType.getUpdatedBy().getUserName(),
                                testType.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static TestTypeDtoList buildTestTypeDataDto(TestType testType) {
        if (testType == null) {
            return null;
        }

        return TestTypeDtoList.builder()
                .id(testType.getId())
                .testName(testType.getTestName())
                .build();
    }

    public static List<TestTypeDtoList> buildTestTypeDtoList(List<TestType> testTypeList) {
        if (CollectionUtils.isEmpty(testTypeList)) {
            return null;
        }

        return testTypeList.stream()
                .map(CommonBuilder::buildTestTypeDataDto)
                .collect(Collectors.toList());
    }

    public static List<TestTypeDto> buildTestTypeDtoFullList(List<TestType> testTypeList) {
        if (CollectionUtils.isEmpty(testTypeList)) {
            return null;
        }

        return testTypeList.stream()
                .map(CommonBuilder::buildTestTypeDto)
                .collect(Collectors.toList());
    }
	
	
    // Build full DisabilityTypeDto
    public static DisabilityTypeDto buildDisabilityTypeDto(DisabilityType disabilityType) {
        if (disabilityType == null) {
            return null;
        }

        return DisabilityTypeDto.builder()
                .id(disabilityType.getId())
                .name(disabilityType.getName())
                .description(disabilityType.getDescription())
                .status(disabilityType.isStatus())
                .insertedDate(disabilityType.getInsertedDate())
                .updatedDate(disabilityType.getUpdatedDate())
                .createdBy(disabilityType.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                disabilityType.getCreatedBy().getUserId(),
                                disabilityType.getCreatedBy().getFullName(),
                                disabilityType.getCreatedBy().getUserName(),
                                disabilityType.getCreatedBy().getMobileNumber()))
                .updatedBy(disabilityType.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                disabilityType.getUpdatedBy().getUserId(),
                                disabilityType.getUpdatedBy().getFullName(),
                                disabilityType.getUpdatedBy().getUserName(),
                                disabilityType.getUpdatedBy().getMobileNumber()))
                .build();
    }

    // Build lightweight list item DTO
    public static DisabilityTypeDtoList buildDisabilityTypeDtoListItem(DisabilityType disabilityType) {
        if (disabilityType == null) {
            return null;
        }

        return DisabilityTypeDtoList.builder()
                .id(disabilityType.getId())
                .name(disabilityType.getName())
                .build();
    }

    // List of lightweight DTOs
    public static List<DisabilityTypeDtoList> buildDisabilityTypeDtoList(List<DisabilityType> disabilityTypes) {
        if (CollectionUtils.isEmpty(disabilityTypes)) {
            return null;
        }

        return disabilityTypes.stream()
                .map(CommonBuilder::buildDisabilityTypeDtoListItem)
                .collect(Collectors.toList());
    }

    // List of full DTOs
    public static List<DisabilityTypeDto> buildDisabilityTypeDtos(List<DisabilityType> disabilityTypes) {
        if (CollectionUtils.isEmpty(disabilityTypes)) {
            return null;
        }

        return disabilityTypes.stream()
                .map(CommonBuilder::buildDisabilityTypeDto)
                .collect(Collectors.toList());
    }
	
    public static ScholarshipSchemeDto buildScholarshipSchemeDto(ScholarshipScheme scheme) {

        if (null == scheme) {
            return null;
        }

        return ScholarshipSchemeDto.builder()
                .id(scheme.getId())
                .schemeName(scheme.getSchemeName())
                .type(scheme.getType())
                .amount(scheme.getAmount())
                .eligibility(scheme.getEligibility())
                .status(scheme.getStatus())
                .createdAt(scheme.getCreatedAt())
                .updatedAt(scheme.getUpdatedAt())
                .createdBy(scheme.getCreatedBy() == null ? null
                        : new UserProfileDtoList(scheme.getCreatedBy().getUserId(),
                                scheme.getCreatedBy().getFullName(),
                                scheme.getCreatedBy().getUserName(),
                                scheme.getCreatedBy().getMobileNumber()))
                .updatedBy(scheme.getUpdatedBy() == null ? null
                        : new UserProfileDtoList(scheme.getUpdatedBy().getUserId(),
                                scheme.getUpdatedBy().getFullName(),
                                scheme.getUpdatedBy().getUserName(),
                                scheme.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static ScholarshipSchemeDtoList buildScholarshipSchemeDataDto(ScholarshipScheme scheme) {

        if (null == scheme) {
            return null;
        }

        return ScholarshipSchemeDtoList.builder()
                .id(scheme.getId())
                .schemeName(scheme.getSchemeName())
                .build();
    }

    public static List<ScholarshipSchemeDtoList> buildScholarshipSchemeDataDtoList(List<ScholarshipScheme> schemeList) {

        if (CollectionUtils.isEmpty(schemeList)) {
            return null;
        }

        return schemeList.stream()
                .map(scheme -> buildScholarshipSchemeDataDto(scheme))
                .collect(Collectors.toList());
    }

    public static List<ScholarshipSchemeDto> buildScholarshipSchemeDtoList(List<ScholarshipScheme> schemeList) {

        if (CollectionUtils.isEmpty(schemeList)) {
            return null;
        }

        return schemeList.stream()
                .map(scheme -> buildScholarshipSchemeDto(scheme))
                .collect(Collectors.toList());
    }
	
	
    // ✅ Convert single BankType to BankTypeDto
    public static BankTypeDto buildBankTypeDto(BankType bankType) {
        if (bankType == null) {
            return null;
        }

        return BankTypeDto.builder()
                .id(bankType.getId())
                .bankName(bankType.getBankName())
                .branchName(bankType.getBranchName())
                .ifscCode(bankType.getIfscCode())
                .status(bankType.getStatus())
                .createdAt(bankType.getCreatedAt())
                .updatedAt(bankType.getUpdatedAt())
                .createdBy(bankType.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                bankType.getCreatedBy().getUserId(),
                                bankType.getCreatedBy().getFullName(),
                                bankType.getCreatedBy().getUserName(),
                                bankType.getCreatedBy().getMobileNumber()))
                .updatedBy(bankType.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                bankType.getUpdatedBy().getUserId(),
                                bankType.getUpdatedBy().getFullName(),
                                bankType.getUpdatedBy().getUserName(),
                                bankType.getUpdatedBy().getMobileNumber()))
                .build();
    }

    // ✅ Convert single BankType to BankTypeDtoList (summary view)
    public static BankTypeDtoList buildBankTypeDtoList(BankType bankType) {
        if (bankType == null) {
            return null;
        }

        return BankTypeDtoList.builder()
                .id(bankType.getId())
                .bankName(bankType.getBankName())
                .build();
    }

    // ✅ Convert List<BankType> to List<BankTypeDto>
    public static List<BankTypeDto> buildBankTypeDtoFullList(List<BankType> bankTypeList) {
        if (CollectionUtils.isEmpty(bankTypeList)) {
            return null;
        }

        return bankTypeList.stream()
                .map(CommonBuilder::buildBankTypeDto)
                .collect(Collectors.toList());
    }

    // ✅ Convert List<BankType> to List<BankTypeDtoList>
    public static List<BankTypeDtoList> buildBankTypeDtoList(List<BankType> bankTypeList) {
        if (CollectionUtils.isEmpty(bankTypeList)) {
            return null;
        }

        return bankTypeList.stream()
                .map(CommonBuilder::buildBankTypeDtoList)
                .collect(Collectors.toList());
    }
	
	
    public static PaymentModeDto buildPaymentModeDto(PaymentMode paymentMode) {

        if (paymentMode == null) {
            return null;
        }

        return PaymentModeDto.builder()
                .id(paymentMode.getId())
                .modeName(paymentMode.getModeName())
                .description(paymentMode.getDescription())
                .status(paymentMode.getStatus())
                .insertedDate(paymentMode.getInsertedDate())
                .updatedDate(paymentMode.getUpdatedDate())
                .createdBy(paymentMode.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                paymentMode.getCreatedBy().getUserId(),
                                paymentMode.getCreatedBy().getFullName(),
                                paymentMode.getCreatedBy().getUserName(),
                                paymentMode.getCreatedBy().getMobileNumber()))
                .updatedBy(paymentMode.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                paymentMode.getUpdatedBy().getUserId(),
                                paymentMode.getUpdatedBy().getFullName(),
                                paymentMode.getUpdatedBy().getUserName(),
                                paymentMode.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static PaymentModeDtoList buildPaymentModeDtoList(PaymentMode paymentMode) {

        if (paymentMode == null) {
            return null;
        }

        return PaymentModeDtoList.builder()
                .id(paymentMode.getId())
                .modeName(paymentMode.getModeName())
                .build();
    }

    public static List<PaymentModeDtoList> buildPaymentModeDtoList(List<PaymentMode> paymentModeList) {

        if (CollectionUtils.isEmpty(paymentModeList)) {
            return null;
        }

        return paymentModeList.stream()
                .map(CommonBuilder::buildPaymentModeDtoList)
                .collect(Collectors.toList());
    }

    public static List<PaymentModeDto> buildPaymentModeDto(List<PaymentMode> paymentModeList) {

        if (CollectionUtils.isEmpty(paymentModeList)) {
            return null;
        }

        return paymentModeList.stream()
                .map(CommonBuilder::buildPaymentModeDto)
                .collect(Collectors.toList());
    }
	
	
    // ✅ Build a single FeesCategoryDto from FeesCategory model
    public static FeesCategoryDto buildFeesCategoryDto(FeesCategory category) {
        if (category == null) {
            return null;
        }

        return FeesCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .status(category.isStatus())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .createdBy(category.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                category.getCreatedBy().getUserId(),
                                category.getCreatedBy().getFullName(),
                                category.getCreatedBy().getUserName(),
                                category.getCreatedBy().getMobileNumber()))
                .updatedBy(category.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                category.getUpdatedBy().getUserId(),
                                category.getUpdatedBy().getFullName(),
                                category.getUpdatedBy().getUserName(),
                                category.getUpdatedBy().getMobileNumber()))
                .build();
    }

    // ✅ Build a single FeesCategoryDtoList from model
    public static FeesCategoryDtoList buildFeesCategoryDtoListData(FeesCategory category) {
        if (category == null) {
            return null;
        }

        return FeesCategoryDtoList.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    // ✅ Build list of FeesCategoryDto from list of model
    public static List<FeesCategoryDto> buildFeesCategoryDtoList(List<FeesCategory> categoryList) {
        if (CollectionUtils.isEmpty(categoryList)) {
            return null;
        }

        return categoryList.stream()
                .map(CommonBuilder::buildFeesCategoryDto)
                .collect(Collectors.toList());
    }

    // ✅ Build list of FeesCategoryDtoList from list of model
    public static List<FeesCategoryDtoList> buildFeesCategoryDtoListData(List<FeesCategory> categoryList) {
        if (CollectionUtils.isEmpty(categoryList)) {
            return null;
        }

        return categoryList.stream()
                .map(CommonBuilder::buildFeesCategoryDtoListData)
                .collect(Collectors.toList());
    }
	
	
	// Converts a FeesType entity to FeesTypeDto
	public static FeesTypeDto buildFeesTypeDto(FeesType feesType) {
	    if (feesType == null) {
	        return null;
	    }

	    return FeesTypeDto.builder()
	            .id(feesType.getId())
	            .name(feesType.getName())
	            .description(feesType.getDescription())
	            .status(feesType.isStatus())
	            .createdAt(feesType.getCreatedAt())
	            .updatedAt(feesType.getUpdatedAt())
	            .createdBy(feesType.getCreatedBy() == null ? null :
	                new UserProfileDtoList(
	                    feesType.getCreatedBy().getUserId(),
	                    feesType.getCreatedBy().getFullName(),
	                    feesType.getCreatedBy().getUserName(),
	                    feesType.getCreatedBy().getMobileNumber()
	                ))
	            .updatedBy(feesType.getUpdatedBy() == null ? null :
	                new UserProfileDtoList(
	                    feesType.getUpdatedBy().getUserId(),
	                    feesType.getUpdatedBy().getFullName(),
	                    feesType.getUpdatedBy().getUserName(),
	                    feesType.getUpdatedBy().getMobileNumber()
	                ))
	            .build();
	}

	// Converts a single FeesType entity to a simpler DTO for dropdown or list
	public static FeesTypeDtoList buildFeesTypeDtoListData(FeesType feesType) {
	    if (feesType == null) {
	        return null;
	    }

	    return FeesTypeDtoList.builder()
	            .id(feesType.getId())
	            .name(feesType.getName())
	            .build();
	}

	// Converts a list of FeesType entities to list of FeesTypeDtoList
	public static List<FeesTypeDtoList> buildFeesTypeDtoList(List<FeesType> feesTypeList) {
	    if (CollectionUtils.isEmpty(feesTypeList)) {
	        return null;
	    }

	    return feesTypeList.stream()
	            .map(CommonBuilder::buildFeesTypeDtoListData)
	            .collect(Collectors.toList());
	}

	// Converts a list of FeesType entities to full DTO list (optional, like AcademicModuleDtoList)
	public static List<FeesTypeDto> buildFeesTypeDtoFullList(List<FeesType> feesTypeList) {
	    if (CollectionUtils.isEmpty(feesTypeList)) {
	        return null;
	    }

	    return feesTypeList.stream()
	            .map(CommonBuilder::buildFeesTypeDto)
	            .collect(Collectors.toList());
	}

	
	public static SubjectDto buildSubjectDto(Subject subject) {
	    if (subject == null) {
	        return null;
	    }

	    return SubjectDto.builder()
	            .id(subject.getId())
	            .subjectName(subject.getSubjectName())
	            .subjectCode(subject.getSubjectCode())
	            .insertedDate(subject.getInsertedDate())
	            .updatedDate(subject.getUpdatedDate())
	            .createdBy(subject.getCreatedBy() == null ? null :
	                    new UserProfileDtoList(
	                            subject.getCreatedBy().getUserId(),
	                            subject.getCreatedBy().getFullName(),
	                            subject.getCreatedBy().getUserName(),
	                            subject.getCreatedBy().getMobileNumber()))
	            .updatedBy(subject.getUpdatedBy() == null ? null :
	                    new UserProfileDtoList(
	                            subject.getUpdatedBy().getUserId(),
	                            subject.getUpdatedBy().getFullName(),
	                            subject.getUpdatedBy().getUserName(),
	                            subject.getUpdatedBy().getMobileNumber()))
	            .build();
	}

	public static List<SubjectDto> buildSubjectDtoList(List<Subject> subjectList) {
	    return subjectList.stream()
	            .map(CommonBuilder::buildSubjectDto)
	            .collect(Collectors.toList());
	}
	
	
	public static GradingSchemeDto buildGradingSchemeDto(GradingScheme scheme) {
        if (scheme == null) {
            return null;
        }

        return GradingSchemeDto.builder()
                .id(scheme.getId())
                .schemeName(scheme.getSchemeName())
                .description(scheme.getDescription())
                .insertedDate(scheme.getInsertedDate())
                .updatedDate(scheme.getUpdatedDate())
                .createdBy(scheme.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                scheme.getCreatedBy().getUserId(),
                                scheme.getCreatedBy().getFullName(),
                                scheme.getCreatedBy().getUserName(),
                                scheme.getCreatedBy().getMobileNumber()))
                .updatedBy(scheme.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                scheme.getUpdatedBy().getUserId(),
                                scheme.getUpdatedBy().getFullName(),
                                scheme.getUpdatedBy().getUserName(),
                                scheme.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static List<GradingSchemeDto> buildGradingSchemeDtoList(List<GradingScheme> schemeList) {
        return schemeList.stream()
                .map(CommonBuilder::buildGradingSchemeDto)
                .collect(Collectors.toList());
    }
	
	
	public static BoardUniversityDto buildBoardUniversityDto(BoardUniversity boardUniversity) {
        if (boardUniversity == null) {
            return null;
        }

        return BoardUniversityDto.builder()
                .id(boardUniversity.getId())
                .name(boardUniversity.getName())
                .type(boardUniversity.getType())
                .insertedDate(boardUniversity.getInsertedDate())
                .updatedDate(boardUniversity.getUpdatedDate())
                .createdBy(boardUniversity.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                boardUniversity.getCreatedBy().getUserId(),
                                boardUniversity.getCreatedBy().getFullName(),
                                boardUniversity.getCreatedBy().getUserName(),
                                boardUniversity.getCreatedBy().getMobileNumber()))
                .updatedBy(boardUniversity.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                boardUniversity.getUpdatedBy().getUserId(),
                                boardUniversity.getUpdatedBy().getFullName(),
                                boardUniversity.getUpdatedBy().getUserName(),
                                boardUniversity.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static List<BoardUniversityDto> buildBoardUniversityDtoList(List<BoardUniversity> boardUniversityList) {
        return boardUniversityList.stream()
                .map(CommonBuilder::buildBoardUniversityDto)
                .collect(Collectors.toList());
    }
	
	
	
	
    public static EducationLevelDto buildEducationLevelDto(EducationLevel educationLevel) {
        if (educationLevel == null) {
            return null;
        }

        return EducationLevelDto.builder()
                .id(educationLevel.getId())
                .levelName(educationLevel.getLevelName())
                .description(educationLevel.getDescription())
                .insertedDate(educationLevel.getInsertedDate())
                .updatedDate(educationLevel.getUpdatedDate())
                .createdBy(educationLevel.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                educationLevel.getCreatedBy().getUserId(),
                                educationLevel.getCreatedBy().getFullName(),
                                educationLevel.getCreatedBy().getUserName(),
                                educationLevel.getCreatedBy().getMobileNumber()))
                .updatedBy(educationLevel.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                educationLevel.getUpdatedBy().getUserId(),
                                educationLevel.getUpdatedBy().getFullName(),
                                educationLevel.getUpdatedBy().getUserName(),
                                educationLevel.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static List<EducationLevelDto> buildEducationLevelDtoList(List<EducationLevel> educationLevelList) {
        return educationLevelList.stream()
                .map(CommonBuilder::buildEducationLevelDto)
                .collect(Collectors.toList());
    }
	
	
	
    public static CertificateTypeDto buildCertificateTypeDto(CertificateType certificateType) {
        if (certificateType == null) {
            return null;
        }

        return CertificateTypeDto.builder()
                .typeId(certificateType.getTypeId())
                .typeCode(certificateType.getTypeCode())
                .certificateName(certificateType.getCertificateName())
                .templatePath(certificateType.getTemplatePath())
                .isPrintable(certificateType.isPrintable())
                .insertedDate(certificateType.getInsertedDate())
                .updatedDate(certificateType.getUpdatedDate())
                .createdBy(certificateType.getCreatedBy() == null ? null :
                        new UserProfileDtoList(
                                certificateType.getCreatedBy().getUserId(),
                                certificateType.getCreatedBy().getFullName(),
                                certificateType.getCreatedBy().getUserName(),
                                certificateType.getCreatedBy().getMobileNumber()))
                .updatedBy(certificateType.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(
                                certificateType.getUpdatedBy().getUserId(),
                                certificateType.getUpdatedBy().getFullName(),
                                certificateType.getUpdatedBy().getUserName(),
                                certificateType.getUpdatedBy().getMobileNumber()))
                .build();
    }

    public static List<CertificateTypeDto> buildCertificateTypeDtoList(List<CertificateType> certificateTypeList) {
        return certificateTypeList.stream()
                .map(CommonBuilder::buildCertificateTypeDto)
                .collect(Collectors.toList());
    }
	
>>>>>>> daccd45 (Initial commit)
	
	
	public static DocumentTypeDto buildDocumentTypeDto(DocumentType documentType) {
	    if (documentType == null) {
	        return null;
	    }

	    return DocumentTypeDto.builder()
	            .documentTypeId(documentType.getDocumentTypeId())
	            .typeCode(documentType.getTypeCode())
	            .documentName(documentType.getDocumentName())
	            .isMandatory(documentType.isMandatory())
	            .insertedDate(documentType.getInsertedDate())
	            .updatedDate(documentType.getUpdatedDate())
	            .createdBy(documentType.getCreatedBy() == null ? null :
	                new UserProfileDtoList(
	                    documentType.getCreatedBy().getUserId(),
	                    documentType.getCreatedBy().getFullName(),
	                    documentType.getCreatedBy().getUserName(),
	                    documentType.getCreatedBy().getMobileNumber()))
	            .updatedBy(documentType.getUpdatedBy() == null ? null :
	                new UserProfileDtoList(
	                    documentType.getUpdatedBy().getUserId(),
	                    documentType.getUpdatedBy().getFullName(),
	                    documentType.getUpdatedBy().getUserName(),
	                    documentType.getUpdatedBy().getMobileNumber()))
	            .build();
	}

	
	public static List<DocumentTypeDto> buildDocumentTypeDtoList(List<DocumentType> documentTypeList) {
	    return documentTypeList.stream()
	            .map(CommonBuilder::buildDocumentTypeDto)
	            .collect(Collectors.toList());
	}
<<<<<<< HEAD

	
	
	public static AcademicYearDto buildAcademicYearDto(AcademicYear academicYear) {
        if (academicYear == null) {
            return null;
        }

        return AcademicYearDto.builder()
            .id(academicYear.getId())
            .name(academicYear.getName())
            .startDate(academicYear.getStartDate())
            .endDate(academicYear.getEndDate())
            .isCurrent(academicYear.getIsCurrent())
            .status(academicYear.getStatus())
            .insertedDate(academicYear.getInsertedDate())
            .updatedDate(academicYear.getUpdatedDate())
            .createdBy(academicYear.getCreatedBy() == null ? null :
                UserProfileDtoList.builder()
                    .userId(academicYear.getCreatedBy().getUserId())
                    .fullName(academicYear.getCreatedBy().getFullName())
                    .userName(academicYear.getCreatedBy().getUserName())
                    .mobileNumber(academicYear.getCreatedBy().getMobileNumber())
                    .build())
            .updatedBy(academicYear.getUpdatedBy() == null ? null :
                UserProfileDtoList.builder()
                    .userId(academicYear.getUpdatedBy().getUserId())
                    .fullName(academicYear.getUpdatedBy().getFullName())
                    .userName(academicYear.getUpdatedBy().getUserName())
                    .mobileNumber(academicYear.getUpdatedBy().getMobileNumber())
                    .build())
            .build();
    }

    public static List<AcademicYearDto> buildAcademicYearDtoList(List<AcademicYear> academicYearList) {
        if (CollectionUtils.isEmpty(academicYearList)) {
            return null;
        }

        return academicYearList.stream()
            .map(CommonBuilder::buildAcademicYearDto)
            .collect(Collectors.toList());
    }
	
	
=======
	
	
	// Full DTO (AcademicYearDto)
	public static AcademicYearDto buildAcademicYearDto(AcademicYear academicYear) {
	    if (academicYear == null) {
	        return null;
	    }

	    return AcademicYearDto.builder()
	            .id(academicYear.getId())
	            .name(academicYear.getName())
	            .startDate(academicYear.getStartDate())
	            .endDate(academicYear.getEndDate())
	            .isCurrent(academicYear.getIsCurrent())
	            .status(academicYear.getStatus())
	            .insertedDate(academicYear.getInsertedDate())
	            .updatedDate(academicYear.getUpdatedDate())
	            .createdBy(academicYear.getCreatedBy() == null ? null
	                    : UserProfileDtoList.builder()
	                        .userId(academicYear.getCreatedBy().getUserId())
	                        .fullName(academicYear.getCreatedBy().getFullName())
	                        .userName(academicYear.getCreatedBy().getUserName())
	                        .mobileNumber(academicYear.getCreatedBy().getMobileNumber())
	                        .build())
	            .updatedBy(academicYear.getUpdatedBy() == null ? null
	                    : UserProfileDtoList.builder()
	                        .userId(academicYear.getUpdatedBy().getUserId())
	                        .fullName(academicYear.getUpdatedBy().getFullName())
	                        .userName(academicYear.getUpdatedBy().getUserName())
	                        .mobileNumber(academicYear.getUpdatedBy().getMobileNumber())
	                        .build())
	            .build();
	}

	// Light DTO (AcademicYearDtoList: id + name only)
	public static AcademicYearDtoList buildAcademicYearDtoList(AcademicYear academicYear) {
	    if (academicYear == null) {
	        return null;
	    }

	    return AcademicYearDtoList.builder()
	            .id(academicYear.getId())
	            .name(academicYear.getName())
	            .build();
	}

	// Full DTO list
	public static List<AcademicYearDto> buildAcademicYearDtoList(List<AcademicYear> academicYearList) {
	    if (CollectionUtils.isEmpty(academicYearList)) {
	        return null;
	    }

	    return academicYearList.stream()
	            .map(CommonBuilder::buildAcademicYearDto)
	            .collect(Collectors.toList());
	}

	// Light DTO list
	public static List<AcademicYearDtoList> buildAcademicYearDtoListLite(List<AcademicYear> academicYearList) {
	    if (CollectionUtils.isEmpty(academicYearList)) {
	        return null;
	    }

	    return academicYearList.stream()
	            .map(CommonBuilder::buildAcademicYearDtoList)
	            .collect(Collectors.toList());
	}

	
	// Full DTO (ReligionDto)
>>>>>>> daccd45 (Initial commit)
	public static ReligionDto buildReligionDto(Religion religion) {
	    if (religion == null) {
	        return null;
	    }

	    return ReligionDto.builder()
	            .religionId(religion.getReligionId())
	            .religionName(religion.getReligionName())
	            .insertedDate(religion.getInsertedDate())
	            .updatedDate(religion.getUpdatedDate())
<<<<<<< HEAD
	            .createdBy(religion.getCreatedBy() == null ? null :
	                    UserProfileDtoList.builder()
	                            .userId(religion.getCreatedBy().getUserId())
	                            .fullName(religion.getCreatedBy().getFullName())
	                            .userName(religion.getCreatedBy().getUserName())
	                            .mobileNumber(religion.getCreatedBy().getMobileNumber())
	                            .build())
	            .updatedBy(religion.getUpdatedBy() == null ? null :
	                    UserProfileDtoList.builder()
	                            .userId(religion.getUpdatedBy().getUserId())
	                            .fullName(religion.getUpdatedBy().getFullName())
	                            .userName(religion.getUpdatedBy().getUserName())
	                            .mobileNumber(religion.getUpdatedBy().getMobileNumber())
	                            .build())
	            .build();
	}

	public static List<ReligionDto> buildReligionDtoList(List<Religion> religions) {
	    if (CollectionUtils.isEmpty(religions)) {
	        return null;
	    }
	    return religions.stream()
=======
	            .createdBy(religion.getCreatedBy() == null ? null
	                    : UserProfileDtoList.builder()
	                        .userId(religion.getCreatedBy().getUserId())
	                        .fullName(religion.getCreatedBy().getFullName())
	                        .userName(religion.getCreatedBy().getUserName())
	                        .mobileNumber(religion.getCreatedBy().getMobileNumber())
	                        .build())
	            .updatedBy(religion.getUpdatedBy() == null ? null
	                    : UserProfileDtoList.builder()
	                        .userId(religion.getUpdatedBy().getUserId())
	                        .fullName(religion.getUpdatedBy().getFullName())
	                        .userName(religion.getUpdatedBy().getUserName())
	                        .mobileNumber(religion.getUpdatedBy().getMobileNumber())
	                        .build())
	            .build();
	}

	// Light DTO (ReligionDtoList: id + name only)
	public static ReligionDtoList buildReligionDtoList(Religion religion) {
	    if (religion == null) {
	        return null;
	    }

	    return ReligionDtoList.builder()
	            .religionId(religion.getReligionId())
	            .religionName(religion.getReligionName())
	            .build();
	}

	// Full DTO list
	public static List<ReligionDto> buildReligionDtoList(List<Religion> religionList) {
	    if (CollectionUtils.isEmpty(religionList)) {
	        return null;
	    }

	    return religionList.stream()
>>>>>>> daccd45 (Initial commit)
	            .map(CommonBuilder::buildReligionDto)
	            .collect(Collectors.toList());
	}

<<<<<<< HEAD
	
	
	
=======
	// Light DTO list
	public static List<ReligionDtoList> buildReligionDtoListLite(List<Religion> religionList) {
	    if (CollectionUtils.isEmpty(religionList)) {
	        return null;
	    }

	    return religionList.stream()
	            .map(CommonBuilder::buildReligionDtoList)
	            .collect(Collectors.toList());
	}


	
	
	
    // Full DTO builder
>>>>>>> daccd45 (Initial commit)
    public static CasteDto buildCasteDto(Caste caste) {
        if (caste == null) {
            return null;
        }

        return CasteDto.builder()
                .casteId(caste.getCasteId())
                .casteName(caste.getCasteName())
                .insertedDate(caste.getInsertedDate())
                .updatedDate(caste.getUpdatedDate())
                .religion(caste.getReligion() == null ? null :
                        ReligionDto.builder()
<<<<<<< HEAD
                            .religionId(caste.getReligion().getReligionId())
                            .religionName(caste.getReligion().getReligionName())
                            .insertedDate(caste.getReligion().getInsertedDate())
                            .updatedDate(caste.getReligion().getUpdatedDate())
                            .build())
                .createdBy(caste.getCreatedBy() == null ? null :
                        UserProfileDtoList.builder()
                            .userId(caste.getCreatedBy().getUserId())
                            .fullName(caste.getCreatedBy().getFullName())
                            .userName(caste.getCreatedBy().getUserName())
                            .mobileNumber(caste.getCreatedBy().getMobileNumber())
                            .build())
                .updatedBy(caste.getUpdatedBy() == null ? null :
                        UserProfileDtoList.builder()
                            .userId(caste.getUpdatedBy().getUserId())
                            .fullName(caste.getUpdatedBy().getFullName())
                            .userName(caste.getUpdatedBy().getUserName())
                            .mobileNumber(caste.getUpdatedBy().getMobileNumber())
                            .build())
                .build();
    }

=======
                                .religionId(caste.getReligion().getReligionId())
                                .religionName(caste.getReligion().getReligionName())
                                .insertedDate(caste.getReligion().getInsertedDate())
                                .updatedDate(caste.getReligion().getUpdatedDate())
                                .createdBy(caste.getReligion().getCreatedBy() == null ? null :
                                        UserProfileDtoList.builder()
                                                .userId(caste.getReligion().getCreatedBy().getUserId())
                                                .fullName(caste.getReligion().getCreatedBy().getFullName())
                                                .userName(caste.getReligion().getCreatedBy().getUserName())
                                                .mobileNumber(caste.getReligion().getCreatedBy().getMobileNumber())
                                                .build())
                                .updatedBy(caste.getReligion().getUpdatedBy() == null ? null :
                                        UserProfileDtoList.builder()
                                                .userId(caste.getReligion().getUpdatedBy().getUserId())
                                                .fullName(caste.getReligion().getUpdatedBy().getFullName())
                                                .userName(caste.getReligion().getUpdatedBy().getUserName())
                                                .mobileNumber(caste.getReligion().getUpdatedBy().getMobileNumber())
                                                .build())
                                .build())
                .createdBy(caste.getCreatedBy() == null ? null :
                        UserProfileDtoList.builder()
                                .userId(caste.getCreatedBy().getUserId())
                                .fullName(caste.getCreatedBy().getFullName())
                                .userName(caste.getCreatedBy().getUserName())
                                .mobileNumber(caste.getCreatedBy().getMobileNumber())
                                .build())
                .updatedBy(caste.getUpdatedBy() == null ? null :
                        UserProfileDtoList.builder()
                                .userId(caste.getUpdatedBy().getUserId())
                                .fullName(caste.getUpdatedBy().getFullName())
                                .userName(caste.getUpdatedBy().getUserName())
                                .mobileNumber(caste.getUpdatedBy().getMobileNumber())
                                .build())
                .build();
    }

    // Full DTO list
>>>>>>> daccd45 (Initial commit)
    public static List<CasteDto> buildCasteDtoList(List<Caste> casteList) {
        if (CollectionUtils.isEmpty(casteList)) {
            return null;
        }
<<<<<<< HEAD
=======

>>>>>>> daccd45 (Initial commit)
        return casteList.stream()
                .map(CommonBuilder::buildCasteDto)
                .collect(Collectors.toList());
    }
<<<<<<< HEAD
	
	
	
	
	public static BloodGroupDto buildBloodGroupDto(BloodGroup bloodGroup) {
		if (bloodGroup == null) {
			return null;
		}
		return BloodGroupDto.builder()
				.id(bloodGroup.getId())
				.bloodGroup(bloodGroup.getBloodGroup())
				.insertedDate(bloodGroup.getInsertedDate())
				.updatedDate(bloodGroup.getUpdatedDate())
				.createdBy(bloodGroup.getCreatedBy() == null ? null
						: UserProfileDtoList.builder()
							.userId(bloodGroup.getCreatedBy().getUserId())
							.fullName(bloodGroup.getCreatedBy().getFullName())
							.userName(bloodGroup.getCreatedBy().getUserName())
							.mobileNumber(bloodGroup.getCreatedBy().getMobileNumber())
							// .email(bloodGroup.getCreatedBy().getEmail()) // if needed
							.build())
				.updatedBy(bloodGroup.getUpdatedBy() == null ? null
						: UserProfileDtoList.builder()
							.userId(bloodGroup.getUpdatedBy().getUserId())
							.fullName(bloodGroup.getUpdatedBy().getFullName())
							.userName(bloodGroup.getUpdatedBy().getUserName())
							.mobileNumber(bloodGroup.getUpdatedBy().getMobileNumber())
							// .email(bloodGroup.getUpdatedBy().getEmail()) // if needed
							.build())
				.build();
	}

	public static List<BloodGroupDto> buildBloodGroupDtoList(List<BloodGroup> bloodGroupList) {
		if (CollectionUtils.isEmpty(bloodGroupList)) {
			return null;
		}
		return bloodGroupList.stream().map(bloodGroup -> buildBloodGroupDto(bloodGroup)).collect(Collectors.toList());
	}
=======

    // Light DTO builder
    public static CasteDtoList buildCasteDtoLite(Caste caste) {
        if (caste == null) {
            return null;
        }

        return CasteDtoList.builder()
                .casteId(caste.getCasteId())
                .casteName(caste.getCasteName())
                .build();
    }

    // Light DTO list
    public static List<CasteDtoList> buildCasteDtoListLite(List<Caste> casteList) {
        if (CollectionUtils.isEmpty(casteList)) {
            return null;
        }

        return casteList.stream()
                .map(CommonBuilder::buildCasteDtoLite)
                .collect(Collectors.toList());
    }
>>>>>>> daccd45 (Initial commit)

	
	
	
	
<<<<<<< HEAD
	public static BatchDto buildBatchDto(Batch batch) {
=======
    public static BloodGroupDto buildBloodGroupDto(BloodGroup bloodGroup) {
        if (bloodGroup == null) {
            return null;
        }

        return BloodGroupDto.builder()
                .id(bloodGroup.getId())
                .bloodGroup(bloodGroup.getBloodGroup())
                .insertedDate(bloodGroup.getInsertedDate())
                .updatedDate(bloodGroup.getUpdatedDate())
                .createdBy(bloodGroup.getCreatedBy() == null ? null
                        : UserProfileDtoList.builder()
                            .userId(bloodGroup.getCreatedBy().getUserId())
                            .fullName(bloodGroup.getCreatedBy().getFullName())
                            .userName(bloodGroup.getCreatedBy().getUserName())
                            .mobileNumber(bloodGroup.getCreatedBy().getMobileNumber())
                            .build())
                .updatedBy(bloodGroup.getUpdatedBy() == null ? null
                        : UserProfileDtoList.builder()
                            .userId(bloodGroup.getUpdatedBy().getUserId())
                            .fullName(bloodGroup.getUpdatedBy().getFullName())
                            .userName(bloodGroup.getUpdatedBy().getUserName())
                            .mobileNumber(bloodGroup.getUpdatedBy().getMobileNumber())
                            .build())
                .build();
    }

    // Light DTO (id + name only)
    public static BloodGroupDtoList buildBloodGroupDtoList(BloodGroup bloodGroup) {
        if (bloodGroup == null) {
            return null;
        }

        return BloodGroupDtoList.builder()
                .id(bloodGroup.getId())
                .bloodGroup(bloodGroup.getBloodGroup())
                .build();
    }

    // Full DTO list
    public static List<BloodGroupDto> buildBloodGroupDtoList(List<BloodGroup> bloodGroupList) {
        if (CollectionUtils.isEmpty(bloodGroupList)) {
            return null;
        }

        return bloodGroupList.stream()
                .map(CommonBuilder::buildBloodGroupDto)
                .collect(Collectors.toList());
    }

    // Light DTO list
    public static List<BloodGroupDtoList> buildBloodGroupDtoListLite(List<BloodGroup> bloodGroupList) {
        if (CollectionUtils.isEmpty(bloodGroupList)) {
            return null;
        }

        return bloodGroupList.stream()
                .map(CommonBuilder::buildBloodGroupDtoList)
                .collect(Collectors.toList());
    }

	
	
	
	
    public static BatchDto buildBatchDto(Batch batch) {
>>>>>>> daccd45 (Initial commit)
        if (batch == null) {
            return null;
        }

        return BatchDto.builder()
                .batchId(batch.getBatchId())
                .batchName(batch.getBatchName())
                .description(batch.getDescription())
                .insertedDate(batch.getInsertedDate())
                .updatedDate(batch.getUpdatedDate())
<<<<<<< HEAD
                .createdBy(batch.getCreatedBy() == null ? null :
                    new com.ws.spring.dto.UserProfileDtoList(
                        batch.getCreatedBy().getUserId(),
                        batch.getCreatedBy().getFullName(),
                        batch.getCreatedBy().getUserName(),
                        batch.getCreatedBy().getMobileNumber()))
                .updatedBy(batch.getUpdatedBy() == null ? null :
                    new com.ws.spring.dto.UserProfileDtoList(
                        batch.getUpdatedBy().getUserId(),
                        batch.getUpdatedBy().getFullName(),
                        batch.getUpdatedBy().getUserName(),
                        batch.getUpdatedBy().getMobileNumber()))
                .build();
    }

    // Build list of BatchDto from list of Batch
    public static List<BatchDto> buildBatchDtoList(List<Batch> batchList) {
=======
                .createdBy(batch.getCreatedBy() == null ? null
                        : UserProfileDtoList.builder()
                            .userId(batch.getCreatedBy().getUserId())
                            .fullName(batch.getCreatedBy().getFullName())
                            .userName(batch.getCreatedBy().getUserName())
                            .mobileNumber(batch.getCreatedBy().getMobileNumber())
                            .build())
                .updatedBy(batch.getUpdatedBy() == null ? null
                        : UserProfileDtoList.builder()
                            .userId(batch.getUpdatedBy().getUserId())
                            .fullName(batch.getUpdatedBy().getFullName())
                            .userName(batch.getUpdatedBy().getUserName())
                            .mobileNumber(batch.getUpdatedBy().getMobileNumber())
                            .build())
                .build();
    }

    public static BatchDtoList buildBatchDtoList(Batch batch) {
        if (batch == null) {
            return null;
        }

        return BatchDtoList.builder()
                .batchId(batch.getBatchId())
                .batchName(batch.getBatchName())
                .build();
    }

    public static List<BatchDto> buildBatchDtoListFull(List<Batch> batchList) {
>>>>>>> daccd45 (Initial commit)
        if (CollectionUtils.isEmpty(batchList)) {
            return null;
        }

        return batchList.stream()
                .map(CommonBuilder::buildBatchDto)
                .collect(Collectors.toList());
    }
<<<<<<< HEAD
=======

    public static List<BatchDtoList> buildBatchDtoListLite(List<Batch> batchList) {
        if (CollectionUtils.isEmpty(batchList)) {
            return null;
        }

        return batchList.stream()
                .map(CommonBuilder::buildBatchDtoList)
                .collect(Collectors.toList());
    }
>>>>>>> daccd45 (Initial commit)
	
	
	
	
	
	
	
    // FacultyProfile builder method (simple)
    public static FacultyProfileDto buildFacultyProfileDto(FacultyProfile faculty) {
        return buildFacultyProfileDto(faculty, true, true);
    }

    // FacultyProfile builder method with load flags
    public static FacultyProfileDto buildFacultyProfileDto(FacultyProfile faculty, boolean loadDepartment, boolean loadDesignation) {
        if (faculty == null) return null;

        DepartmentDto departmentDto = null;
        if (loadDepartment && faculty.getDepartment() != null) {
            Department department = faculty.getDepartment();
            departmentDto = new DepartmentDto(
                    department.getDepartmentId(),
                    department.getDepartmentName(),
                    department.getDescription(),
                    department.getInsertedDate(),
                    department.getUpdatedDate(),
                    department.getCreatedBy(),
                    department.getUpdatedBy()
            );
        }

        JobDesignationDto designationDto = null;
        if (loadDesignation && faculty.getDesignation() != null) {
            JobDesignation designation = faculty.getDesignation();
            designationDto = new JobDesignationDto(
                    designation.getDesignationId(),
                    designation.getDesignationName(),
                    designation.getDescription(),
                    designation.getInsertedDate(),
                    designation.getUpdatedDate(),
                    designation.getCreatedBy(),
                    designation.getUpdatedBy()
            );
        }

        UserProfile createdBy = faculty.getCreatedBy();
        UserProfile updatedBy = faculty.getUpdatedBy();

        UserProfileDtoList createdByDto = (createdBy == null) ? null :
                new UserProfileDtoList(createdBy.getUserId(), createdBy.getFullName(), createdBy.getUserName(), createdBy.getMobileNumber());

        UserProfileDtoList updatedByDto = (updatedBy == null) ? null :
                new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(), updatedBy.getMobileNumber());

        return FacultyProfileDto.builder()
                .id(faculty.getId())
                .firstName(faculty.getFirstName())
                .middleName(faculty.getMiddleName())
                .lastName(faculty.getLastName())
                .gender(faculty.getGender())
                .qualification(faculty.getQualification())
                .email(faculty.getEmail())
                .fatherName(faculty.getFatherName())
                .motherName(faculty.getMotherName())
                .insertedDate(faculty.getInsertedDate())
                .updatedDate(faculty.getUpdatedDate())
                .department(departmentDto)
                .designation(designationDto)
                .createdBy(createdByDto)
                .updatedBy(updatedByDto)
                .build();
    }

    // List builder for FacultyProfile
    public static List<FacultyProfileDto> buildFacultyProfileDtoList(List<FacultyProfile> facultyList) {
        if (CollectionUtils.isEmpty(facultyList)) return null;

        return facultyList.stream()
                .map(CommonBuilder::buildFacultyProfileDto)
                .collect(Collectors.toList());
    }
	
<<<<<<< HEAD
	public static StudentProfileDto buildStudentProfileDto(StudentProfile student) {
	    return buildStudentProfileDto(student, true, true);
	}

	public static StudentProfileDto buildStudentProfileDto(StudentProfile student, boolean loadCourse, boolean loadSubject) {
	    if (student == null) return null;

	    CourseDto courseDto = null;
	    if (loadCourse && student.getCourse() != null) {
	        courseDto = buildCourseDto(student.getCourse(), false); // Avoid deep loading
	    }

	    CourseSubjectDto subjectDto = null;
	    if (loadSubject && student.getCourseSubject() != null) {
	        subjectDto = buildCourseSubjectDto(student.getCourseSubject(), false); // Avoid deep loading
	    }

	    UserProfile createdBy = student.getCreatedBy();
	    UserProfile updatedBy = student.getUpdatedBy();

	    UserProfileDtoList createdByDto = (createdBy == null) ? null :
	            new UserProfileDtoList(createdBy.getUserId(), createdBy.getFullName(), createdBy.getUserName(), createdBy.getMobileNumber());

	    UserProfileDtoList updatedByDto = (updatedBy == null) ? null :
	            new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(), updatedBy.getMobileNumber());

	    return StudentProfileDto.builder()
	            .id(student.getId())
	            .firstName(student.getFirstName())
	            .middleName(student.getMiddleName())
	            .lastName(student.getLastName())
	            .gender(student.getGender())
	            .fatherName(student.getFatherName())
	            .motherName(student.getMotherName())
	            .insertedDate(student.getInsertedDate())
	            .updatedDate(student.getUpdatedDate())
	            .course(courseDto)
	            .courseSubject(subjectDto)
	            .createdBy(createdByDto)
	            .updatedBy(updatedByDto)
	            .build();
	}

	public static List<StudentProfileDto> buildStudentProfileDtoList(List<StudentProfile> studentList) {
	    if (CollectionUtils.isEmpty(studentList)) return null;
	    return studentList.stream()
	            .map(CommonBuilder::buildStudentProfileDto)
	            .collect(Collectors.toList());
	}

	
=======
    // Full DTO with toggle for relations
    public static StudentProfileDto buildStudentProfileDto(StudentProfile profile, boolean loadRelations) {
        if (profile == null) return null;

        return StudentProfileDto.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .middleName(profile.getMiddleName())
                .lastName(profile.getLastName())
                .gender(profile.getGender())
                .fatherName(profile.getFatherName())
                .motherName(profile.getMotherName())
                .insertedDate(profile.getInsertedDate())
                .updatedDate(profile.getUpdatedDate())
                .fileName(profile.getFileName()) // ✅ new
                .filePath(profile.getFilePath()) // ✅ new
                .course(loadRelations ? CommonBuilder.buildCourseDtoList(profile.getCourse()) : null)
                .courseSubject(loadRelations ? CommonBuilder.buildCourseSubjectDtoListLight(profile.getCourseSubject()) : null)
                .bloodGroup(loadRelations ? CommonBuilder.buildBloodGroupDtoList(profile.getBloodGroup()) : null)
                .batch(loadRelations ? CommonBuilder.buildBatchDtoList(profile.getBatch()) : null)
                .academicYear(loadRelations ? CommonBuilder.buildAcademicYearDtoList(profile.getAcademicYear()) : null)
                .country(loadRelations ? CommonBuilder.buildCountryDtoListLight(profile.getCountry()) : null)
                .religion(loadRelations ? CommonBuilder.buildReligionDtoList(profile.getReligion()) : null)
                .caste(loadRelations ? CommonBuilder.buildCasteDtoLite( profile.getCaste()) : null)
                .state(loadRelations ? CommonBuilder.buildStateDtoListData(profile.getState()) : null)
                .city(loadRelations ? CommonBuilder.buildCityDtoListData(profile.getCity()) : null)
                .createdBy(profile.getCreatedBy() == null ? null :
                        new UserProfileDtoList(profile.getCreatedBy().getUserId(),
                                profile.getCreatedBy().getFullName(),
                                profile.getCreatedBy().getUserName(),
                                profile.getCreatedBy().getMobileNumber()))
                .updatedBy(profile.getUpdatedBy() == null ? null :
                        new UserProfileDtoList(profile.getUpdatedBy().getUserId(),
                                profile.getUpdatedBy().getFullName(),
                                profile.getUpdatedBy().getUserName(),
                                profile.getUpdatedBy().getMobileNumber()))
                .build();
    }

    // Shortcut for full load
    public static StudentProfileDto buildStudentProfileDto(StudentProfile profile) {
        return buildStudentProfileDto(profile, true);
    }

    // Light DTO
    public static StudentProfileDtoList buildStudentProfileDtoList(StudentProfile profile) {
        if (profile == null) return null;

        return StudentProfileDtoList.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .build();
    }

    public static List<StudentProfileDto> buildStudentProfileDtoListFull(List<StudentProfile> list) {
        if (CollectionUtils.isEmpty(list)) return null;

        return list.stream()
                .map(CommonBuilder::buildStudentProfileDto) // use the correct class here
                .collect(Collectors.toList());
    }

    // List of light DTOs
    public static List<StudentProfileDtoList> buildStudentProfileDtoList(List<StudentProfile> list) {
        if (CollectionUtils.isEmpty(list)) return null;

        return list.stream()
                .map(CommonBuilder::buildStudentProfileDtoList) // use the correct class here
                .collect(Collectors.toList());
    }


	
 // Full DTO
>>>>>>> daccd45 (Initial commit)
    public static CourseDto buildCourseDto(Course course) {
        return buildCourseDto(course, true);
    }

    public static CourseDto buildCourseDto(Course course, boolean loadSubjects) {
        if (course == null) return null;

        List<CourseSubjectDto> courseSubjects = null;
        if (loadSubjects && !CollectionUtils.isEmpty(course.getCourseSubjects())) {
            courseSubjects = course.getCourseSubjects().stream()
                    .map(subject -> buildCourseSubjectDto(subject, false))
                    .collect(Collectors.toList());
        }

        return CourseDto.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseSubjects(courseSubjects)
                .build();
    }

<<<<<<< HEAD
    public static List<CourseDto> buildCourseDtoList(List<Course> courseList) {
        if (CollectionUtils.isEmpty(courseList)) return null;
        return courseList.stream().map(CommonBuilder::buildCourseDto).collect(Collectors.toList());
    }

=======
    // Light DTO
    public static CourseDtoList buildCourseDtoList(Course course) {
        if (course == null) return null;

        return CourseDtoList.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .build();
    }

    // Full DTO list
    public static List<CourseDto> buildCourseDtoListFull(List<Course> courseList) {
        if (CollectionUtils.isEmpty(courseList)) return null;

        return courseList.stream()
                .map(CommonBuilder::buildCourseDto)
                .collect(Collectors.toList());
    }

    // Light DTO list
    public static List<CourseDtoList> buildCourseDtoList(List<Course> courseList) {
        if (CollectionUtils.isEmpty(courseList)) return null;

        return courseList.stream()
                .map(CommonBuilder::buildCourseDtoList)
                .collect(Collectors.toList());
    }



 // Full DTO
>>>>>>> daccd45 (Initial commit)
    public static CourseSubjectDto buildCourseSubjectDto(CourseSubject subject) {
        return buildCourseSubjectDto(subject, true);
    }

    public static CourseSubjectDto buildCourseSubjectDto(CourseSubject subject, boolean loadCourse) {
        if (subject == null) return null;

        return CourseSubjectDto.builder()
                .courseSubjectId(subject.getCourseSubjectId())
                .subjectName(subject.getSubjectName())
<<<<<<< HEAD
                .course(loadCourse && subject.getCourse() != null ? buildCourseDto(subject.getCourse(), false) : null)
                .build();
    }

    public static List<CourseSubjectDto> buildCourseSubjectDtoList(List<CourseSubject> subjectList) {
        if (CollectionUtils.isEmpty(subjectList)) return null;
        return subjectList.stream().map(CommonBuilder::buildCourseSubjectDto).collect(Collectors.toList());
    }

=======
                .course(loadCourse && subject.getCourse() != null 
                    ? buildCourseDtoList(subject.getCourse()) // ✅ FIXED
                    : null)
                .build();
    }


    // Light DTO
    public static CourseSubjectDtoList buildCourseSubjectDtoListLight(CourseSubject subject) {
        if (subject == null) return null;

        return CourseSubjectDtoList.builder()
                .courseSubjectId(subject.getCourseSubjectId())
                .subjectName(subject.getSubjectName())
                .build();
    }

    // Full list
    public static List<CourseSubjectDto> buildCourseSubjectDtoListFull(List<CourseSubject> subjectList) {
        if (CollectionUtils.isEmpty(subjectList)) return null;

        return subjectList.stream()
                .map(CommonBuilder::buildCourseSubjectDto)
                .collect(Collectors.toList());
    }

    // Light list
    public static List<CourseSubjectDtoList> buildCourseSubjectDtoListLight(List<CourseSubject> subjectList) {
        if (CollectionUtils.isEmpty(subjectList)) return null;

        return subjectList.stream()
                .map(CommonBuilder::buildCourseSubjectDtoListLight)
                .collect(Collectors.toList());
    }


>>>>>>> daccd45 (Initial commit)
	

	    
	    
	    
	public static JobDesignationDto buildJobDesignationDto(JobDesignation jobDesignation) {
		if (null == jobDesignation) {
			return null;
		}
		return JobDesignationDto.builder().designationId(jobDesignation.getDesignationId()).designationName(jobDesignation.getDesignationName())
				.description(jobDesignation.getDescription())
				

				.build();
	}

	public static List<JobDesignationDto> buildJobDesignationDtoList(List<JobDesignation> designationList) {
		if (CollectionUtils.isEmpty(designationList)) {
			return null;
		}return designationList.stream().map(jobDesignation -> buildJobDesignationDto(jobDesignation)).collect(Collectors.toList());
	}

	public static LocationDto buildLocationDto(Location location) {
		if (null == location) {
			return null;
		}
		return LocationDto.builder().locationId(location.getLocationId()).locationName(location.getLocationName())
				.discription(location.getDiscription())
				

				.build();
	}

	public static List<LocationDto> buildLocationDtoList(List<Location> locationList) {
		if (CollectionUtils.isEmpty(locationList)) {
			return null;
		}return locationList.stream().map(location -> buildLocationDto(location)).collect(Collectors.toList());
	}

	public static QualificationDto buildQualificationDto(Qualification qualification) {
		if (null == qualification) {
			return null;
		}
		return QualificationDto.builder().qualificationId(qualification.getQualificationId()).qualificationName(qualification.getQualificationName())
				.description(qualification.getDescription())
				

				.build();
	}

	public static List<QualificationDto> buildQualificationDtoList(List<Qualification> qualificationList) {
		if (CollectionUtils.isEmpty(qualificationList)) {
			return null;
		}return qualificationList.stream().map(qualification -> buildQualificationDto(qualification)).collect(Collectors.toList());
	}

	
	
	public static DepartmentDto buildDepartmentDto(Department department) {
		if (null == department) {
			return null;
		}
		return DepartmentDto.builder().departmentId(department.getDepartmentId()).departmentName(department.getDepartmentName())
				.description(department.getDescription())
				.insertedDate(department.getInsertedDate())
				.updatedDate(department.getUpdatedDate())
				.createdBy(department.getCreatedBy() == null ? null
						: UserProfileDtoList.builder().userId(department.getCreatedBy().getUserId())
						.fullName(department.getCreatedBy().getFullName())
						.userName(department.getCreatedBy().getUserName())
						.mobileNumber(department.getCreatedBy().getMobileNumber())
						//          .email(department.getCreatedBy().getEmail())

						.build())
				.updatedBy(department.getUpdatedBy() == null ? null
						: UserProfileDtoList.builder().userId(department.getUpdatedBy().getUserId())
						.fullName(department.getUpdatedBy().getFullName())
						.userName(department.getUpdatedBy().getUserName())
						.mobileNumber(department.getUpdatedBy().getMobileNumber())
						//          .email(department.getUpdatedBy().getEmail())

						.build())

				.build();
	}

	public static List<DepartmentDto> buildDepartmentDtoList(List<Department> departmentList) {
		if (CollectionUtils.isEmpty(departmentList)) {
			return null;
		}
		return departmentList.stream().map(department -> buildDepartmentDto(department)).collect(Collectors.toList());
	}
	
	
	
	public static OccupationDto buildOccupationDto(Occupation occupation) {
		// TODO Auto-generated method stub
		if (null == occupation) {
			return null;
		}
		return OccupationDto.builder().occupationId(occupation.getOccupationId())
				.occupationName(occupation.getOccupationName())
				.description(occupation.getDescription())
				.insertedDate(occupation.getInsertedDate())
				.updatedDate(occupation.getUpdatedDate())


				.createdBy(occupation.getCreatedBy() == null ? null
						: UserProfileDtoList.builder().userId(occupation.getCreatedBy().getUserId())
						.userName(occupation.getCreatedBy().getUserName())

						.build())
				.updatedBy(occupation.getUpdatedBy() == null ? null
						: UserProfileDtoList.builder().userId(occupation.getUpdatedBy().getUserId())
						.userName(occupation.getUpdatedBy().getUserName())
						.build())



				.build();
	}
	



	public static AdvertisementDto buildAdvertisementDto(Advertisement advertisement) {
		if (null == advertisement) {
            return null;
        }
        return AdvertisementDto.builder().advertisementId(advertisement.getAdvertisementId())
        		.advertisementName(advertisement.getAdvertisementName())
        		.fileName(advertisement.getFileName())
        		.filePath(advertisement.getFilePath())
                .description(advertisement.getDescription())
                .insertedDate(advertisement.getInsertedDate())
                .updatedDate(advertisement.getUpdatedDate())
               
                .build();
	}

	public static List<AdvertisementDto> buildAdvertisementDtoList(List<Advertisement> advertisementList) {
		if (CollectionUtils.isEmpty(advertisementList)) {
            return null;
        }
        return advertisementList.stream().map(advertisement -> buildAdvertisementDto(advertisement)).collect(Collectors.toList());
	}

	
	public static NewsDto buildNewsDto(News news) {
		if (null == news) {
	        return null;
	    }
	    return NewsDto.builder().newsId(news.getNewsId())
	    		.newsName(news.getNewsName())
	    		.photoName(news.getPhotoName())
	    		.photoPath(news.getPhotoPath())
	            .description(news.getDescription())
	            .insertedDate(news.getInsertedDate())
	            .updatedDate(news.getUpdatedDate())
	            
	       
	            .build();
	}
	
	public static PromoDto buildPromoDto(Promo promo) {
		
		if (null == promo) {
	        return null;
	    }
	    return PromoDto.builder().promoId(promo.getPromoId())
	    		.promoName(promo.getPromoName())
	            .description(promo.getDescription())
	            .youTube(promo.getYouTube())
	            .insertedDate(promo.getInsertedDate())
	            .updatedDate(promo.getUpdatedDate())
	            
	    

	           
	            
	            .build();
	}

	public static SuccessStoryDto buildSuccessStoryDto(SuccessStory successStory) {
		if (null == successStory) {
	        return null;
	    }
	    return SuccessStoryDto.builder().successstoryId(successStory.getSuccessstoryId())
	    		.successstoryName(successStory.getSuccessstoryName())
	    		.photoName(successStory.getPhotoName())
	    		.photoPath(successStory.getPhotoPath())
	            .description(successStory.getDescription())
	            .insertedDate(successStory.getInsertedDate())
	            .updatedDate(successStory.getUpdatedDate())
	            
	            
	          
	            
	            .build();
	}

	public static UserProfileDto buildUserProfileDto(UserProfile userProfile) {
		if (null == userProfile) {
            return null;
        }
        return UserProfileDto.builder().userId(userProfile.getUserId())
        	    .userName(userProfile.getUserName())
        	    .mobileNumber(userProfile.getMobileNumber())
        	    .email(userProfile.getEmail())
                .insertedDate(userProfile.getInsertedDate())
                .updatedDate(userProfile.getUpdatedDate())
                .createdBy(userProfile.getCreatedBy() == null ? null
	 	                   : UserProfileDtoList.builder().userId(userProfile.getCreatedBy().getUserId())
	 	                   .userName(userProfile.getCreatedBy().getUserName())
	 	                   
	 	                   .build())
                .updatedBy(userProfile.getUpdatedBy() == null ? null
	 	                   : UserProfileDtoList.builder().userId(userProfile.getUpdatedBy().getUserId())
	 	                   .userName(userProfile.getUpdatedBy().getUserName())
	 	                   .build())
                
                .roleDto(userProfile.getRole() == null ? null
	 	                   : RoleDto.builder().roleId(userProfile.getRole().getRoleId())
	 	                   .roleName(userProfile.getRole().getRoleName())
	 	                   .build())

                .build();
	}

	
<<<<<<< HEAD
	public static CityDto buildCityDto(City city) {
		// TODO Auto-generated method stub
		if (null == city) {
			return null;
		}
		return CityDto.builder().cityId(city.getCityId())
				.cityName(city.getCityName())
				.description(city.getDescription())
				.insertedDate(city.getInsertedDate())
				.updatedDate(city.getUpdatedDate())


//				.createdBy(city.getCreatedBy() == null ? null
//						: UserProfileDtoList.builder().userId(city.getCreatedBy().getUserId())
//						.userName(city.getCreatedBy().getUserName())
//
//						.build())
//				.updatedBy(city.getUpdatedBy() == null ? null
//						: UserProfileDtoList.builder().userId(city.getUpdatedBy().getUserId())
//						.userName(city.getUpdatedBy().getUserName())
//						.build())



				.build();
	}

	
	
	
	
	public static List<CityDto> buildCityDtoList(List<City> cityList) {
		
		if (CollectionUtils.isEmpty(cityList)) {
	        return null;
	    }
	    return cityList.stream().map(city -> buildCityDto(city)).collect(Collectors.toList());
	}

	
	public static CountryDto buildCountryDto(Country country) {
		// TODO Auto-generated method stub
		if (null == country) {
			return null;
		}
		return CountryDto.builder().countryId(country.getCountryId())
				.countryName(country.getCountryName())
				.description(country.getDescription())
				.insertedDate(country.getInsertedDate())
				.updatedDate(country.getUpdatedDate())


				/*
				 * .createdBy(country.getCreatedBy() == null ? null :
				 * UserProfileDtoList.builder().userId(country.getCreatedBy().getUserId())
				 * .userName(country.getCreatedBy().getUserName())
				 * 
				 * .build()) .updatedBy(country.getUpdatedBy() == null ? null :
				 * UserProfileDtoList.builder().userId(country.getUpdatedBy().getUserId())
				 * .userName(country.getUpdatedBy().getUserName()) .build())
				 */



				.build();
	}
	
	
	public static StateDto buildStateDto(State state) {
		if (null == state) {
			return null;
		}
		return StateDto.builder().stateId(state.getStateId())
				.stateName(state.getStateName())
				.description(state.getDescription())
				.insertedDate(state.getInsertedDate())
				.updatedDate(state.getUpdatedDate())
				/*
				 * .createdBy(state.getCreatedBy() == null ? null :
				 * UserProfileDtoList.builder().userId(state.getCreatedBy().getUserId())
				 * .userName(state.getCreatedBy().getUserName())
				 * 
				 * .build()) .updatedBy(state.getUpdatedBy() == null ? null :
				 * UserProfileDtoList.builder().userId(state.getUpdatedBy().getUserId())
				 * .userName(state.getUpdatedBy().getUserName()) .build())
				 */

				.build();
	}

=======
    // Convert single City entity to full CityDto
    // Convert single City entity to full CityDto
    public static CityDto buildCityDto(City city) {
        if (city == null) {
            return null;
        }

        return CityDto.builder()
                .cityId(city.getCityId())
                .cityName(city.getCityName())
                .pincode(city.getPincode())
                .description(city.getDescription())
                .insertedDate(city.getInsertedDate())
                .updatedDate(city.getUpdatedDate())
                .createdBy(city.getCreatedBy() == null ? null :
                    new UserProfileDtoList(
                        city.getCreatedBy().getUserId(),
                        city.getCreatedBy().getFullName(),
                        city.getCreatedBy().getUserName(),
                        city.getCreatedBy().getMobileNumber()))
                .updatedBy(city.getUpdatedBy() == null ? null :
                    new UserProfileDtoList(
                        city.getUpdatedBy().getUserId(),
                        city.getUpdatedBy().getFullName(),
                        city.getUpdatedBy().getUserName(),
                        city.getUpdatedBy().getMobileNumber()))
                .state(city.getState() == null ? null :
                    new StateDtoList(
                        city.getState().getStateId(),
                        city.getState().getStateName()))
                .build();
    }

    // Convert single City entity to lightweight CityDtoList
    public static CityDtoList buildCityDtoListData(City city) {
        if (city == null) {
            return null;
        }

        return CityDtoList.builder()
                .cityId(city.getCityId())
                .cityName(city.getCityName())
                .pincode(city.getPincode())
                .build();
    }

    // Convert List<City> to List<CityDto>
    public static List<CityDto> buildCityDtoFullList(List<City> cityList) {
        if (CollectionUtils.isEmpty(cityList)) {
            return null;
        }

        return cityList.stream()
                .map(CommonBuilder::buildCityDto)
                .collect(Collectors.toList());
    }

    // Convert List<City> to List<CityDtoList> (used in getAllCities())
    public static List<CityDtoList> buildCityDtoList(List<City> cityList) {
        if (CollectionUtils.isEmpty(cityList)) {
            return null;
        }

        return cityList.stream()
                .map(CommonBuilder::buildCityDtoListData)
                .collect(Collectors.toList());
    }
	
	
    // Full DTO
    public static CountryDto buildCountryDto(Country country) {
        return buildCountryDto(country, true);
    }

    public static CountryDto buildCountryDto(Country country, boolean loadUserProfiles) {
        if (country == null) return null;

        return CountryDto.builder()
                .countryId(country.getCountryId())
                .countryName(country.getCountryName())
                .description(country.getDescription())
                .insertedDate(country.getInsertedDate())
                .updatedDate(country.getUpdatedDate())
                .createdBy(loadUserProfiles && country.getCreatedBy() != null
                        ? UserProfileDtoList.builder()
                            .userId(country.getCreatedBy().getUserId())
                            .fullName(country.getCreatedBy().getFullName())
                            .userName(country.getCreatedBy().getUserName())
                            .mobileNumber(country.getCreatedBy().getMobileNumber())
                            .build()
                        : null)
                .updatedBy(loadUserProfiles && country.getUpdatedBy() != null
                        ? UserProfileDtoList.builder()
                            .userId(country.getUpdatedBy().getUserId())
                            .fullName(country.getUpdatedBy().getFullName())
                            .userName(country.getUpdatedBy().getUserName())
                            .mobileNumber(country.getUpdatedBy().getMobileNumber())
                            .build()
                        : null)
                .build();
    }

    // ✅ Fix: Add this method to resolve "undefined for the type CommonBuilder"
    public static CountryDtoList buildCountryDtoList(Country country) {
        return buildCountryDtoListLight(country); // Just reuses the light version
    }

    // Light DTO
    public static CountryDtoList buildCountryDtoListLight(Country country) {
        if (country == null) return null;

        return CountryDtoList.builder()
                .countryId(country.getCountryId())
                .countryName(country.getCountryName())
                .build();
    }

    // Full list
    public static List<CountryDto> buildCountryDtoListFull(List<Country> countryList) {
        if (CollectionUtils.isEmpty(countryList)) return null;

        return countryList.stream()
                .map(CommonBuilder::buildCountryDto)
                .collect(Collectors.toList());
    }

    // Light list
    public static List<CountryDtoList> buildCountryDtoListLight(List<Country> countryList) {
        if (CollectionUtils.isEmpty(countryList)) return null;

        return countryList.stream()
                .map(CommonBuilder::buildCountryDtoListLight)
                .collect(Collectors.toList());
    }
    // Optional: Full method for list of light Dto (shortcut)
    public static List<CountryDtoList> buildCountryDtoList(List<Country> countryList) {
        return buildCountryDtoListLight(countryList);
    }
    
    
    
    
    // Convert single State to StateDto
    // Build detailed StateDto with optional user profile loading
    // Convert single State to full StateDto with UserProfile association (optional)
    public static StateDto buildStateDto(State state, boolean loadUserProfiles) {
        if (state == null) return null;

        return StateDto.builder()
                .stateId(state.getStateId())
                .stateName(state.getStateName())
                .description(state.getDescription())
                .insertedDate(state.getInsertedDate())
                .updatedDate(state.getUpdatedDate())
                .createdBy(loadUserProfiles && state.getCreatedBy() != null
                        ? buildUserProfileDtoList(state.getCreatedBy())
                        : null)
                .updatedBy(loadUserProfiles && state.getUpdatedBy() != null
                        ? buildUserProfileDtoList(state.getUpdatedBy())
                        : null)
                .country(state.getCountry() != null
                ? buildCountryDtoList(state.getCountry())
                : null) // ✅ Add this line to set country properly
                .build();
    }

    // Convert single State to StateDtoList (lightweight)
    public static StateDtoList buildStateDtoListData(State state) {
        if (state == null) return null;

        return StateDtoList.builder()
                .stateId(state.getStateId())
                .stateName(state.getStateName())
                .build();
    }

    // Overloaded method with default: load user profiles = true
    public static StateDto buildStateDto(State state) {
        return buildStateDto(state, true);
    }

    // Convert list of State to list of StateDtoList
    public static List<StateDtoList> buildStateDtoList(List<State> stateList) {
        return stateList.stream()
                .map(CommonBuilder::buildStateDtoListData)
                .collect(Collectors.toList());
    }

    // Convert UserProfile to UserProfileDtoList (minimal user info)
    public static UserProfileDtoList buildUserProfileDtoList(UserProfile user) {
        if (user == null) return null;

        return UserProfileDtoList.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .userName(user.getUserName())
                .mobileNumber(user.getMobileNumber())
                .build();
    }
>>>>>>> daccd45 (Initial commit)
	
	


	
	
	
	public static LanguageDto buildLanguageDto(Language language) {
		if (null == language) {
            return null;
        }
        return LanguageDto.builder().languageId(language.getLanguageId())
        		.languageName(language.getLanguageName())
                .description(language.getDescription())
                .insertedDate(language.getInsertedDate())
                .updatedDate(language.getUpdatedDate())
 

                .build();
	}
	
	public static List<LanguageDto> buildLanguageDtoList(List<Language> languageList) {
		if (CollectionUtils.isEmpty(languageList)) {
	        return null;
	    }
	    return languageList.stream().map(language -> buildLanguageDto(language)).collect(Collectors.toList());
	}

	public static SkillDto buildSkillDto(Skill skill) {
		if (skill == null) {
	        return null;
	    }

	    return SkillDto.builder()
	            .skillId(skill.getSkillId())
	            .skillName(skill.getSkillName())
	            .description(skill.getDescription())
	            .insertedDate(skill.getInsertedDate()) // Assuming LocalDateTime
	            .updatedDate(skill.getUpdatedDate())   // Assuming LocalDateTime
	            .build();
	}

	public static List<SkillDto> buildSkillDtoList(List<Skill> skillList) {
		if (CollectionUtils.isEmpty(skillList)) {
	        return null;
	    }
	    return skillList.stream().map(skill -> buildSkillDto(skill)).collect(Collectors.toList());
	}
	
	
	
	
	public static AcademicTopicDto buildAcademicTopicDto(AcademicTopic academicTopic) {
		if (null == academicTopic) {
	        return null;
	    }
	    return AcademicTopicDto.builder().topicId(academicTopic.getTopicId())
	    		.topicName(academicTopic.getTopicName())
	    		.description(academicTopic.getDescription())
	    		.videoUrl(academicTopic.getVideoUrl())
	           
	    		.academicModuleDtoList(academicTopic.getAcademicModule() == null ? null
		                   : AcademicModuleDtoList.builder().moduleId(academicTopic.getAcademicModule().getModuleId())
		                    .moduleName(academicTopic.getAcademicModule().getModuleName())
		                   
		                   .build())
	            .build();
	}


	public static List<AcademicTopicDto> buildAcademicTopicDtoList(List<AcademicTopic> academicTopicList) {
		
		if (CollectionUtils.isEmpty(academicTopicList)) {
	        return null;
	    }
	    return academicTopicList.stream().map(academicTopic -> buildAcademicTopicDto(academicTopic)).collect(Collectors.toList());
	    
	}

	public static AcademicModuleDto buildAcademicModuleDto(AcademicModule academicModule) {
		
		if (null == academicModule) {
	        return null;
	    }
	    return AcademicModuleDto.builder().moduleId(academicModule.getModuleId())
	    		.moduleName(academicModule.getModuleName())
	            .description(academicModule.getDescription())
	            
	            .academicCourseDtoList(CollectionUtils.isEmpty(academicModule.getAcademicCourses()) ? null
						: academicModule.getAcademicCourses().stream()
								.map(course -> AcademicCourseDtoList.builder().courseId(course.getCourseId())
										.courseName(course.getCourseName())
									//	.description(course.getDescription())
										.build())
								.collect(Collectors.toList()))

	            .build();
	}


	public static AcademicModuleDtoList buildAcademicModuleDataDto(AcademicModule academicModule) {
		
		if (null == academicModule) {
	        return null;
	    }
	    return AcademicModuleDtoList.builder().moduleId(academicModule.getModuleId())
	    		.moduleName(academicModule.getModuleName())
	           

	            .build();
	}

	public static List<AcademicModuleDtoList> buildAcademicModuleDataDtoList(List<AcademicModule> academicModuleList) {
		
		if (CollectionUtils.isEmpty(academicModuleList)) {
	        return null;
	    }
	    return academicModuleList.stream().map(academicModule -> buildAcademicModuleDataDto(academicModule)).collect(Collectors.toList());
	}
	public static List<AcademicModuleDto> buildAcademicModuleDtoList(List<AcademicModule> academicModuleList) {
		
		if (CollectionUtils.isEmpty(academicModuleList)) {
	        return null;
	    }
	    return academicModuleList.stream().map(academicModule -> buildAcademicModuleDto(academicModule)).collect(Collectors.toList());
	}


	public static AcademicCourseDtoList buildAcademicCourseDto(AcademicCourse academicCourse) {
		if (null == academicCourse) {
	        return null;
	    }
	    return AcademicCourseDtoList.builder().courseId(academicCourse.getCourseId())
	    		.courseName(academicCourse.getCourseName())
				/*
				 * .description(academicCourse.getDescription())
				 * .courseMrp(academicCourse.getCourseMrp())
				 * .discount(academicCourse.getDiscount())
				 * .sellingPrice(academicCourse.getSellingPrice())
				 * .handlingFee(academicCourse.getHandlingFee())
				 * .trailPeriod(academicCourse.getTrailPeriod())
				 * .subscriptionDays(academicCourse.getSubscriptionDays())
				 * .videoUrl(academicCourse.getVideoUrl())
				 */

	           
	            
	            .build();
	}
	


	public static List<AcademicCourseDtoList> buildAcademicCourseDtoList(Set<AcademicCourse> academicCourseDtoList) {
		
		if (CollectionUtils.isEmpty(academicCourseDtoList)) {
	        return null;
	    }
	    return academicCourseDtoList.stream().map(academicCourse -> buildAcademicCourseDto(academicCourse)).collect(Collectors.toList());
	    
	}

	public static AcademicCategoryDto buildAcademicCategoryDto(AcademicCategory category) {
		
		if (null == category) {
	        return null;
	    }
	    return AcademicCategoryDto.builder().categoryId(category.getCategoryId())
	    		.categoryName(category.getCategoryName())
	    		.description(category.getDescription())

	           
	            
	            .build();
	    
	}

	public static AcademicCategoryDtoList buildAcademicCategoryDtoData(AcademicCategory category) {
		
		if (null == category) {
	        return null;
	    }
	    return AcademicCategoryDtoList.builder().categoryId(category.getCategoryId())
	    		.categoryName(category.getCategoryName())

	           
	            
	            .build();
	    
	}

	
		public static AcademicCourseDto buildAcademicCourseDataDto(AcademicCourse academicCourse) {
		
		if (null == academicCourse) {
	        return null;
	    }
	    return AcademicCourseDto.builder().courseId(academicCourse.getCourseId())
	    		.courseName(academicCourse.getCourseName())
	            .description(academicCourse.getDescription())
	            .courseMrp(academicCourse.getCourseMrp())
	    		.discount(academicCourse.getDiscount())
	    		.sellingPrice(academicCourse.getSellingPrice())
	    		.handlingFee(academicCourse.getHandlingFee())
	            .trailPeriod(academicCourse.getTrailPeriod())
	            .subscriptionDays(academicCourse.getSubscriptionDays())
	            .videoUrl(academicCourse.getVideoUrl())
	            .academicCategoryDtoList(academicCourse.getAcademicCategory() == null ? null
		                   : AcademicCategoryDtoList.builder().categoryId(academicCourse.getAcademicCategory().getCategoryId())
		                   .categoryName(academicCourse.getAcademicCategory().getCategoryName())
		                   
		                   .build())
	            .build();
	}

	public static List<AcademicCategoryDtoList> buildAcademicCategoryDtoList(List<AcademicCategory> categoryList) {
		
		if (CollectionUtils.isEmpty(categoryList)) {
	        return null;
	    }
	    return categoryList.stream().map(category -> buildAcademicCategoryDtoData(category)).collect(Collectors.toList());
	}
	
	public static List<AcademicCourseDto> buildAcademicCourseDataDtoList(List<AcademicCourse> academicCourseList) {
		if (CollectionUtils.isEmpty(academicCourseList)) {
	        return null;
	    }
	    return academicCourseList.stream().map(academicCourse -> buildAcademicCourseDataDto(academicCourse)).collect(Collectors.toList());
	}
	
	
	
	
	public static UpskillCategoryDto buildUpskillCategoryDto(UpskillCategory category) {
		if (null == category) {
	        return null;
	    }
	    return UpskillCategoryDto.builder().categoryId(category.getCategoryId())
	    		.categoryName(category.getCategoryName())
	    		.description(category.getDescription())
	 
	           
	            
	            .build();
	    
	}
	 
	public static UpskillCategoryDtoList buildUpskillCategoryDtoData(UpskillCategory category) {
		if (null == category) {
	        return null;
	    }
	    return UpskillCategoryDtoList.builder().categoryId(category.getCategoryId())
	    		.categoryName(category.getCategoryName())    
	            .build();
	    
	}
	public static List<UpskillCategoryDtoList> buildUpskillCategoryList(List<UpskillCategory> categoryList) {
		
		if (CollectionUtils.isEmpty(categoryList)) {
	        return null;
	    }
	    return categoryList.stream().map(category -> buildUpskillCategoryDtoData(category)).collect(Collectors.toList());
	}
	 
	public static UpskillCourseDTo buildUpskillCourseDto(UpskillCourse upskillCourse) {
		
		if (null == upskillCourse) {
	        return null;
	    }
	    return UpskillCourseDTo.builder().courseId(upskillCourse.getCourseId())
	    		.courseName(upskillCourse.getCourseName())
	    		.description(upskillCourse.getDescription())
	    		.courseMrp(upskillCourse.getCourseMrp())
	    		.discount(upskillCourse.getDiscount())
	    		.sellingPrice(upskillCourse.getSellingPrice())
	    		.handlingFee(upskillCourse.getHandlingFee())
	    		.trailPeriod(upskillCourse.getTrailPeriod())
	    		.subscriptionDays(upskillCourse.getSubscriptionDays())
	    		.videoUrl(upskillCourse.getVideoUrl())
	 
	    		.upskillCategoryDtoList(upskillCourse.getUpskillCategory() == null ? null
		                   : UpskillCategoryDtoList.builder().categoryId(upskillCourse.getUpskillCategory().getCategoryId())
		                   .categoryName(upskillCourse.getUpskillCategory().getCategoryName())
		                   
		                   .build())
	            
	            .build();
	}
	public static List<UpskillCourseDTo> buildUpskillCourseDtoList(List<UpskillCourse> upskillCourseList) {
		
		if (CollectionUtils.isEmpty(upskillCourseList)) {
	        return null;
	    }
	    return upskillCourseList.stream().map(upskillCourse -> buildUpskillCourseDto(upskillCourse)).collect(Collectors.toList());
	    
	}
	public static UpskillCourseDToList buildUpskillCourseDataDto(UpskillCourse upskillCourse) {
		
		if (null == upskillCourse) {
	        return null;
	    }
	    return UpskillCourseDToList.builder().courseId(upskillCourse.getCourseId())
	    		.courseName(upskillCourse.getCourseName())
	 
	            .build();
	}
	 
	public static UpskillTopicDto buildUpskillTopicDto(UpskillTopic upskillTopic) {
		if (null == upskillTopic) {
	        return null;
	    }
	    return UpskillTopicDto.builder().topicId(upskillTopic.getTopicId())
	    		.topicName(upskillTopic.getTopicName())
	    		.description(upskillTopic.getDescription())
	    		.videoUrl(upskillTopic.getVideoUrl())
	           
	    		.upskillModuleDtoList(upskillTopic.getUpskillModule() == null ? null
		                   : UpskillModuleDtoList.builder().moduleId(upskillTopic.getUpskillModule().getModuleId())
		                    .moduleName(upskillTopic.getUpskillModule().getModuleName())
		                   
		                   .build())
	            .build();
	}
	public static List<UpskillTopicDto> buildUpskillTopicDtoList(List<UpskillTopic> upskillTopicList) {
		if (CollectionUtils.isEmpty(upskillTopicList)) {
	        return null;
	    }
	    return upskillTopicList.stream().map(upskillTopic -> buildUpskillTopicDto(upskillTopic)).collect(Collectors.toList());
	}
	 
	 
	public static UpskillModuleDto buildUpskillModuleDto(UpskillModule upskillModule) {
		if (null == upskillModule) {
	        return null;
	    }
	    return UpskillModuleDto.builder().moduleId(upskillModule.getModuleId())
	    		.moduleName(upskillModule.getModuleName())
	            .description(upskillModule.getDescription())
	            
	            .upskillCourseDToList(CollectionUtils.isEmpty(upskillModule.getUpskillCourses()) ? null
						: upskillModule.getUpskillCourses().stream()
								.map(course -> UpskillCourseDToList.builder().courseId(course.getCourseId())
										.courseName(course.getCourseName())
									//	.description(course.getDescription())
										.build())
								.collect(Collectors.toList()))
	 
	            .build();
	}
	 
	public static UpskillModuleDtoList buildUpskillModuleDataDto(UpskillModule upskillModule) {
		if (null == upskillModule) {
	        return null;
	    }
	    return UpskillModuleDtoList.builder().moduleId(upskillModule.getModuleId())
	    		.moduleName(upskillModule.getModuleName())
	            .build();
	}
	public static List<UpskillModuleDtoList> buildUpskillModuleDataDtoList(List<UpskillModule> moduleList) {
		if (CollectionUtils.isEmpty(moduleList)) {
	        return null;
	    }
	    return moduleList.stream().map(upskillModule -> buildUpskillModuleDataDto(upskillModule)).collect(Collectors.toList());
	}
	public static List<UpskillModuleDto> buildUpskillModuleDtoList(List<UpskillModule> moduleList) {
		if (CollectionUtils.isEmpty(moduleList)) {
	        return null;
	    }
	    return moduleList.stream().map(upskillModule -> buildUpskillModuleDto(upskillModule)).collect(Collectors.toList());
	}
	public static List<UpskillCourseDToList> buildUpskillCourseDToList(Set<UpskillCourse> upskillCourseDToList) {
		
		if (CollectionUtils.isEmpty(upskillCourseDToList)) {
	        return null;
	    }
	    return upskillCourseDToList.stream().map(upskillCourse -> buildUpskillCourseDataDto(upskillCourse)).collect(Collectors.toList());
	}
	public static List<UpskillCourseDToList> buildUpskillCourseDataDToList(List<UpskillCourse> upskillCourseDToList) {
		
		if (CollectionUtils.isEmpty(upskillCourseDToList)) {
	        return null;
	    }
	    return upskillCourseDToList.stream().map(upskillCourse -> buildUpskillCourseDataDto(upskillCourse)).collect(Collectors.toList());
	}

	
}
