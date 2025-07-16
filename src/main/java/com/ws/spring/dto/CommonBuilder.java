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
import com.ws.spring.model.Batch;
import com.ws.spring.model.BloodGroup;
import com.ws.spring.model.Caste;
import com.ws.spring.model.City;
import com.ws.spring.model.Country;
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.model.Department;
import com.ws.spring.model.DocumentType;
import com.ws.spring.model.FacultyProfile;
import com.ws.spring.model.JobDesignation;
import com.ws.spring.model.Language;
import com.ws.spring.model.Location;
import com.ws.spring.model.News;
import com.ws.spring.model.Occupation;
import com.ws.spring.model.Promo;
import com.ws.spring.model.Qualification;
import com.ws.spring.model.Religion;
import com.ws.spring.model.Skill;
import com.ws.spring.model.State;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.model.SuccessStory;
import com.ws.spring.model.UpskillCategory;
import com.ws.spring.model.UpskillCourse;
import com.ws.spring.model.UpskillModule;
import com.ws.spring.model.UpskillTopic;
import com.ws.spring.model.UserProfile;

public class CommonBuilder {
	
	
	
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
	
	
	public static ReligionDto buildReligionDto(Religion religion) {
	    if (religion == null) {
	        return null;
	    }

	    return ReligionDto.builder()
	            .religionId(religion.getReligionId())
	            .religionName(religion.getReligionName())
	            .insertedDate(religion.getInsertedDate())
	            .updatedDate(religion.getUpdatedDate())
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
	            .map(CommonBuilder::buildReligionDto)
	            .collect(Collectors.toList());
	}

	
	
	
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

    public static List<CasteDto> buildCasteDtoList(List<Caste> casteList) {
        if (CollectionUtils.isEmpty(casteList)) {
            return null;
        }
        return casteList.stream()
                .map(CommonBuilder::buildCasteDto)
                .collect(Collectors.toList());
    }
	
	
	
	
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

	
	
	
	
	public static BatchDto buildBatchDto(Batch batch) {
        if (batch == null) {
            return null;
        }

        return BatchDto.builder()
                .batchId(batch.getBatchId())
                .batchName(batch.getBatchName())
                .description(batch.getDescription())
                .insertedDate(batch.getInsertedDate())
                .updatedDate(batch.getUpdatedDate())
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
        if (CollectionUtils.isEmpty(batchList)) {
            return null;
        }

        return batchList.stream()
                .map(CommonBuilder::buildBatchDto)
                .collect(Collectors.toList());
    }
	
	
	
	
	
	
	
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

    public static List<CourseDto> buildCourseDtoList(List<Course> courseList) {
        if (CollectionUtils.isEmpty(courseList)) return null;
        return courseList.stream().map(CommonBuilder::buildCourseDto).collect(Collectors.toList());
    }

    public static CourseSubjectDto buildCourseSubjectDto(CourseSubject subject) {
        return buildCourseSubjectDto(subject, true);
    }

    public static CourseSubjectDto buildCourseSubjectDto(CourseSubject subject, boolean loadCourse) {
        if (subject == null) return null;

        return CourseSubjectDto.builder()
                .courseSubjectId(subject.getCourseSubjectId())
                .subjectName(subject.getSubjectName())
                .course(loadCourse && subject.getCourse() != null ? buildCourseDto(subject.getCourse(), false) : null)
                .build();
    }

    public static List<CourseSubjectDto> buildCourseSubjectDtoList(List<CourseSubject> subjectList) {
        if (CollectionUtils.isEmpty(subjectList)) return null;
        return subjectList.stream().map(CommonBuilder::buildCourseSubjectDto).collect(Collectors.toList());
    }

	

	    
	    
	    
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
