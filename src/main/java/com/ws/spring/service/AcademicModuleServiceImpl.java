package com.ws.spring.service;
 
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
 
import com.ws.spring.dto.AcademicCourseDtoList;
import com.ws.spring.dto.AcademicModuleDto;
import com.ws.spring.dto.AcademicModuleDtoList;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.AcademicCourse;
import com.ws.spring.model.AcademicModule;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.AcademicCourseRepository;
import com.ws.spring.repository.AcademicModuleRepository;
import com.ws.spring.repository.UserProfileRepository;
 
@Service
public class AcademicModuleServiceImpl {
 
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
	@Autowired
	AcademicModuleRepository  academicModuleRepository;
	@Autowired
	AcademicCourseRepository academicCourseRepository;
	@Autowired
	UserProfileRepository userProfileRepository;
 
	
	public AcademicModuleDto getAcademicModuleByModuleId(long moduleId) {
		AcademicModule academicModule = academicModuleRepository.findByModuleId(moduleId);
		return CommonBuilder.buildAcademicModuleDto(academicModule);
	}
 
	public List<AcademicModuleDto> getAcademicModulesByCourseId(long courseId) {
		AcademicCourse academicCourse = academicCourseRepository.findByCourseId(courseId);
		List<AcademicModule> academicModuleList = academicModuleRepository.findAllByAcademicCourses(academicCourse);
		return CommonBuilder.buildAcademicModuleDtoList(academicModuleList);
	}
 
	public Page<AcademicModuleDto> getAllAcademicModulesByPagination(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("moduleId").descending());
		Page<AcademicModule> advertisementPage = academicModuleRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();
        return new PageImpl<AcademicModuleDto>(advertisementPage
                .stream()
                .map(academicModule -> new AcademicModuleDto(
                		academicModule.getModuleId(),
                		academicModule.getModuleName(),
                		academicModule.getDescription(), academicModule.getInsertedDate(), academicModule.getUpdatedDate(), academicModule.getCreatedBy(), academicModule.getUpdatedBy(), academicModule.getAcademicCourses()))
                .collect(Collectors.toList()), pageable, totalElements);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public AcademicModule createAcademicModule(AcademicModuleDto moduleDto) {
		AcademicModule module = new AcademicModule();
		 BeanUtils.copyProperties(moduleDto, module,"createdBy","updatedBy");
		 UserProfile userProfile = userProfileRepository.findByUserId(moduleDto.getCreatedBy().getUserId());
		 module.setCreatedBy(userProfile);
		 module.setUpdatedBy(userProfile);
		 Set<AcademicCourse> courses = null;
			List<AcademicCourseDtoList> associateCourseList = moduleDto.getAcademicCourseDtoList();		
					if (!CollectionUtils.isEmpty(associateCourseList)) {
				List<Long> ids = associateCourseList.stream().map(AcademicCourseDtoList::getCourseId).collect(Collectors.toList());
				courses = academicCourseRepository.findAllById(ids).stream().collect(Collectors.toSet());
 
				module.setAcademicCourses(courses);

			}

		return academicModuleRepository.save(module) ;
	}
 
	@Transactional(propagation = Propagation.REQUIRED)
	public AcademicModule updateAcademicModule(AcademicModuleDto moduleDto) {
		AcademicModule module = academicModuleRepository.findByModuleId(moduleDto.getModuleId());
		try {
			module.setModuleName(moduleDto.getModuleName());
			module.setDescription(moduleDto.getDescription());


			} catch (Exception e) {
				logger.error(" Error while updating Module {} and the Error is : {}", moduleDto.getModuleName(),
						e.getMessage());
			}

		UserProfile userProfile = userProfileRepository.findByUserId(moduleDto.getUpdatedBy().getUserId());
		module.setUpdatedBy(userProfile);
		List<AcademicCourseDtoList> associateCourseList = moduleDto.getAcademicCourseDtoList();
		if (!CollectionUtils.isEmpty(associateCourseList)) {
			List<Long> ids = associateCourseList.stream().map(AcademicCourseDtoList::getCourseId).collect(Collectors.toList());
			List<AcademicCourse> newCourses = academicCourseRepository.findAllById(ids);
 
 
			Set<AcademicCourse> originalCourse = module.getAcademicCourses();
			if (!CollectionUtils.isEmpty(newCourses)) {
				if (!CollectionUtils.isEmpty(originalCourse)) {
					originalCourse.removeAll(newCourses);
				}
				module.setAcademicCourses(originalCourse);

 
			}
 
 
		} else {
			Set<AcademicCourse> originalCourse = module.getAcademicCourses();
			if (!CollectionUtils.isEmpty(originalCourse)) {
				module.setAcademicCourses(null);
			}
		}
			return academicModuleRepository.save(module) ;
	}
 
	public void deleteAcademicModuleById(long moduleId) {
		academicModuleRepository.deleteById(moduleId);
	}
 
	public List<AcademicModuleDtoList> getAllAcademicModules() {
		List<AcademicModule> academicModuleList = academicModuleRepository.findAll();
		return CommonBuilder.buildAcademicModuleDataDtoList(academicModuleList);
	}
 
}