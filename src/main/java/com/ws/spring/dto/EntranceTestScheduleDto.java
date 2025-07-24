package com.ws.spring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.ws.spring.model.EntranceTestSchedule;
import com.ws.spring.model.TestCenter;
import com.ws.spring.model.TestType;
import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class EntranceTestScheduleDto {

	private Long id;

	private TestTypeDtoList testType;

	private LocalDate testDate;

	private LocalTime startTime;

	private LocalTime endTime;

	private TestCenterDtoList testCenter;

	private String remarks;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private UserProfileDtoList createdBy;

	private UserProfileDtoList updatedBy;

	public EntranceTestScheduleDto(Long id, TestType testType, LocalDate testDate, LocalTime startTime, LocalTime endTime,
			TestCenter testCenter, String remarks, LocalDateTime createdAt, LocalDateTime updatedAt,
			UserProfile createdBy, UserProfile updatedBy) {
		super();
		this.id = id;
		this.testDate = testDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remarks = remarks;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;

		if (testType == null) {
			this.testType = null;
		} else {
			this.testType = new TestTypeDtoList(testType.getId(), testType.getTestName());
		}

		if (testCenter == null) {
			this.testCenter = null;
		} else {
			this.testCenter = new TestCenterDtoList(testCenter.getId(), testCenter.getCenterName());
		}

		if (createdBy == null) {
			this.createdBy = null;
		} else {
			this.createdBy = new UserProfileDtoList(createdBy.getUserId(), createdBy.getFullName(), createdBy.getUserName(),
					createdBy.getMobileNumber());
		}

		if (updatedBy == null) {
			this.updatedBy = null;
		} else {
			this.updatedBy = new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(),
					updatedBy.getMobileNumber());
		}
	}

	public EntranceTestScheduleDto(Long id, TestTypeDtoList testType, LocalDate testDate, LocalTime startTime,
			LocalTime endTime, TestCenterDtoList testCenter, String remarks, LocalDateTime createdAt,
			LocalDateTime updatedAt, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
		super();
		this.id = id;
		this.testType = testType;
		this.testDate = testDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.testCenter = testCenter;
		this.remarks = remarks;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}
}
