package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.AcademicYearDto;
<<<<<<< HEAD
=======
import com.ws.spring.dto.AcademicYearDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.AcademicYear;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.AcademicYearRepository;
import com.ws.spring.repository.UserProfileRepository;

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

@Service
public class AcademicYearServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
<<<<<<< HEAD
    AcademicYearRepository academicYearRepository;

    @Autowired
    UserProfileRepository userProfileRepository;
=======
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;
>>>>>>> daccd45 (Initial commit)

    public AcademicYearDto getAcademicYearById(long id) {
        AcademicYear year = academicYearRepository.findById(id);
        return CommonBuilder.buildAcademicYearDto(year);
    }

    public AcademicYear getAcademicYearByName(String name) {
        return academicYearRepository.findByName(name);
    }

<<<<<<< HEAD
    public List<AcademicYearDto> getAllAcademicYears() {
        List<AcademicYear> yearList = academicYearRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return yearList.stream()
                .map(CommonBuilder::buildAcademicYearDto)
=======
    public List<AcademicYearDtoList> getAllAcademicYears() {
        List<AcademicYear> yearList = academicYearRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return yearList.stream()
                .map(year -> new AcademicYearDtoList(year.getId(), year.getName()))
>>>>>>> daccd45 (Initial commit)
                .collect(Collectors.toList());
    }

    public Page<AcademicYearDto> getAllAcademicYearsByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<AcademicYear> yearPage = academicYearRepository.findAll(pageable);
        int totalElements = (int) yearPage.getTotalElements();

        List<AcademicYearDto> dtoList = yearPage.stream()
                .map(year -> new AcademicYearDto(
                        year.getId(),
                        year.getName(),
                        year.getStartDate(),
                        year.getEndDate(),
                        year.getIsCurrent(),
                        year.getStatus(),
                        year.getInsertedDate(),
                        year.getUpdatedDate(),
                        year.getCreatedBy(),
                        year.getUpdatedBy()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AcademicYear createAcademicYear(AcademicYearDto dto) {
        AcademicYear year = new AcademicYear();
        BeanUtils.copyProperties(dto, year, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        year.setCreatedBy(user);
        year.setUpdatedBy(user);

<<<<<<< HEAD
        year.setIsCurrent(dto.getIsCurrent()); // triggers status set automatically

=======
        year.setIsCurrent(dto.getIsCurrent()); // Automatically set status in entity logic
>>>>>>> daccd45 (Initial commit)
        return academicYearRepository.save(year);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AcademicYear updateAcademicYear(AcademicYearDto dto) {
        AcademicYear year = academicYearRepository.findById(dto.getId());

        try {
            year.setName(dto.getName());
            year.setStartDate(dto.getStartDate());
            year.setEndDate(dto.getEndDate());
<<<<<<< HEAD
            year.setIsCurrent(dto.getIsCurrent()); // triggers status update
=======
            year.setIsCurrent(dto.getIsCurrent());
>>>>>>> daccd45 (Initial commit)
        } catch (Exception e) {
            logger.error("Error while updating AcademicYear {}: {}", dto.getName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        year.setUpdatedBy(user);

        return academicYearRepository.save(year);
    }

    public void deleteAcademicYearById(long id) {
        academicYearRepository.deleteById(id);
    }
}
