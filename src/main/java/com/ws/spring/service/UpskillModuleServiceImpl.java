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
 
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.UpskillCourseDToList;
import com.ws.spring.dto.UpskillModuleDto;
import com.ws.spring.dto.UpskillModuleDtoList;
import com.ws.spring.model.UpskillCourse;
import com.ws.spring.model.UpskillModule;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.UpskillCourseRepository;
import com.ws.spring.repository.UpskillModuleRepository;
import com.ws.spring.repository.UserProfileRepository;
 
@Service
public class UpskillModuleServiceImpl {
 
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
	@Autowired
	UpskillModuleRepository  upskillModuleRepository;
	@Autowired
	UpskillCourseRepository  upskillCourseRepository;
	@Autowired
	UserProfileRepository userProfileRepository;

//	public UpskillModuleDto getUpskillModuleByModuleId(long moduleId)
//	{
//		
//		UpskillModule upskillModule = upskillModuleRepository.findByModuleId(moduleId);
//		return CommonBuilder.buildUpskillModuleDto(upskillModule);
//		
//	}

 
	public List<UpskillModuleDto> getUpskillModulesByCourseId(long courseId) {
		UpskillCourse upskillCourse = upskillCourseRepository.findByCourseId(courseId);
		List<UpskillModule> moduleList = upskillModuleRepository.findAllByUpskillCourses(upskillCourse);
		return CommonBuilder.buildUpskillModuleDtoList(moduleList);
	}

 
	public Page<UpskillModuleDto> getAllUpskillModulesByPagination(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("moduleId").descending());
		Page<UpskillModule> advertisementPage = upskillModuleRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();
        return new PageImpl<UpskillModuleDto>(advertisementPage
                .stream()
                .map(upskillModule -> new UpskillModuleDto(
                		upskillModule.getModuleId(),
                		upskillModule.getModuleName(),
                		upskillModule.getDescription(), upskillModule.getInsertedDate(), upskillModule.getUpdatedDate(), upskillModule.getCreatedBy(), upskillModule.getUpdatedBy(), upskillModule.getUpskillCourses()))
                .collect(Collectors.toList()), pageable, totalElements);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillModule createUpskillModule(UpskillModuleDto moduleDto) {
		 UpskillModule module = new UpskillModule();
		 BeanUtils.copyProperties(moduleDto, module,"createdBy","updatedBy");
		 UserProfile userProfile = userProfileRepository.findByUserId(moduleDto.getCreatedBy().getUserId());
		 module.setCreatedBy(userProfile);
		 module.setUpdatedBy(userProfile);
		 Set<UpskillCourse> courses = null;
			List<UpskillCourseDToList> associateCourseList = moduleDto.getUpskillCourseDToList();		
					if (!CollectionUtils.isEmpty(associateCourseList)) {
				List<Long> ids = associateCourseList.stream().map(UpskillCourseDToList::getCourseId).collect(Collectors.toList());
				courses = upskillCourseRepository.findAllById(ids).stream().collect(Collectors.toSet());
 
				module.setUpskillCourses(courses);

			}

		return upskillModuleRepository.save(module) ;
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillModule updateUpskillModule(UpskillModuleDto moduleDto) {
		UpskillModule module = upskillModuleRepository.findByModuleId(moduleDto.getModuleId());
		try {
			module.setModuleName(moduleDto.getModuleName());
			module.setDescription(moduleDto.getDescription());


			} catch (Exception e) {
				logger.error(" Error while updating Module {} and the Error is : {}", moduleDto.getModuleName(),
						e.getMessage());
			}

		UserProfile userProfile = userProfileRepository.findByUserId(moduleDto.getUpdatedBy().getUserId());
		module.setUpdatedBy(userProfile);
		List<UpskillCourseDToList> associateCourseList = moduleDto.getUpskillCourseDToList();
		if (!CollectionUtils.isEmpty(associateCourseList)) {
			List<Long> ids = associateCourseList.stream().map(UpskillCourseDToList::getCourseId).collect(Collectors.toList());
			List<UpskillCourse> newCourses = upskillCourseRepository.findAllById(ids);
 
 
			Set<UpskillCourse> originalCourse = module.getUpskillCourses();
			if (!CollectionUtils.isEmpty(newCourses)) {
				if (!CollectionUtils.isEmpty(originalCourse)) {
					originalCourse.removeAll(newCourses);
				}
				module.setUpskillCourses(newCourses.stream().collect(Collectors.toSet()));

 
			}
 
 
		} else {
			Set<UpskillCourse> originalCourse = module.getUpskillCourses();
			if (!CollectionUtils.isEmpty(originalCourse)) {
				module.setUpskillCourses(null);
			}
		}
			return upskillModuleRepository.save(module) ;
	}
 
	public void deleteUpskillModuleById(long moduleId) {
		upskillModuleRepository.deleteById(moduleId);
	}
 
	public List<UpskillModuleDtoList> getAllUpskillModules() {
        List<UpskillModule> moduleList = upskillModuleRepository.findAll();
		return CommonBuilder.buildUpskillModuleDataDtoList(moduleList);
	}
 
	



}