package com.ws.spring.service;

import java.util.List;
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

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.DepartmentDto;
import com.ws.spring.model.Department;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.DepartmentRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class DepartmentServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public Department createDepartment(DepartmentDto departmentDto) {
		Department department = new Department();
		BeanUtils.copyProperties(departmentDto, department);
		departmentRepository.save(department);
		UserProfile userProfile = userProfileRepository.findByUserId(departmentDto.getCreatedBy().getUserId());
		department.setCreatedBy(userProfile);
		department.setUpdatedBy(userProfile);
		
		return departmentRepository.save(department);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Department updateDepartment(DepartmentDto departmentDto) {
		Department department = departmentRepository.findByDepartmentId(departmentDto.getDepartmentId());
	
		try {
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setDescription(departmentDto.getDescription());
		
		//departmentRepository.save(department);
		
		UserProfile userProfile = userProfileRepository.findByUserId(departmentDto.getUpdatedBy().getUserId());
		department.setUpdatedBy(userProfile);
		
		} catch (Exception e) {
			logger.error(" Error while creating department {} and the Error is : {}", departmentDto.getDepartmentName(),
					e.getMessage());
		}
		
		return departmentRepository.save(department);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDepartmentById(long departmentId) {
		departmentRepository.deleteById(departmentId);
	}


		
//		departmentRepository.save(department);
		
	

	public DepartmentDto queryDepartmentByDepartmentId(long departmentId) {
		Department department = departmentRepository.findByDepartmentId(departmentId);
        return CommonBuilder.buildDepartmentDto(department);
	}

	public List<DepartmentDto> queryAllDepartments() {
		List<Department> departmentList = departmentRepository.findAll(Sort.by(Sort.Direction.DESC, "insertedDate"));
        return CommonBuilder.buildDepartmentDtoList(departmentList);
	}

	public long getDepartmentCount() {
		return departmentRepository.count();
	}

	

	public Department queryByDepartmentName(String departmentName) {
		return departmentRepository.findByDepartmentName(departmentName);
	}
	

	

	public Page<DepartmentDto> getAllDepartmentsByPagination(int pageNumber, int pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Department> departmentPage = departmentRepository.findAll(pageable);
		
        int totalElements = (int) departmentPage.getTotalElements();
        
        return new PageImpl<DepartmentDto>(departmentPage
                .stream()
                .map(department -> new DepartmentDto(
                		department.getDepartmentId(),
                		department.getDepartmentName(), department.getDescription(), department.getInsertedDate(), department.getUpdatedDate(), department.getCreatedBy(), department.getUpdatedBy()))
                     
                .collect(Collectors.toList()), pageable, totalElements);
	}

	public boolean checkDepartNameExists(String departmentName) {
		// TODO Auto-generated method stub
		return false;
	}

}
