package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
import com.ws.spring.dto.StudentProfileDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.CourseRepository;
import com.ws.spring.repository.CourseSubjectRepository;
import com.ws.spring.repository.StudentProfileRepository;
import com.ws.spring.repository.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
=======
import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ws.common.util.StringUtil;
import com.ws.spring.dto.StudentProfileDto;
import com.ws.spring.dto.StudentProfileDtoList;
import com.ws.spring.dto.UserProfileDtoList;
import com.ws.spring.model.*;
import com.ws.spring.repository.*;
>>>>>>> daccd45 (Initial commit)

@Service
public class StudentProfileServiceImpl {

<<<<<<< HEAD
=======
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

>>>>>>> daccd45 (Initial commit)
    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

<<<<<<< HEAD
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSubjectRepository courseSubjectRepository;

    public StudentProfileDto getStudentProfileById(long id) {
        StudentProfile profile = studentProfileRepository.findById(id);
        return CommonBuilder.buildStudentProfileDto(profile);
    }

    public List<StudentProfileDto> getAllStudentProfiles() {
        List<StudentProfile> profileList = studentProfileRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return profileList.stream()
                .map(CommonBuilder::buildStudentProfileDto)
=======
    @Autowired private CourseRepository courseRepository;
    @Autowired private CourseSubjectRepository courseSubjectRepository;
    @Autowired private BloodGroupRepository bloodGroupRepository;
    @Autowired private BatchRepository batchRepository;
    @Autowired private AcademicYearRepository academicYearRepository;
    @Autowired private CountryRepository countryRepository;
    @Autowired private ReligionRepository religionRepository;
    @Autowired private CasteRepository casteRepository;
    @Autowired private StateRepository stateRepository;
    @Autowired private CityRepository cityRepository;

    @Autowired
    private FileStorageService fileStoreService;

    public StudentProfileDto getStudentProfileById(long id) {
        StudentProfile profile = studentProfileRepository.findById(id);
        return profile == null ? null : new StudentProfileDto(
                profile.getId(),
                profile.getFirstName(),
                profile.getMiddleName(),
                profile.getLastName(),
                profile.getGender(),
                profile.getFatherName(),
                profile.getMotherName(),
                profile
        );
    }

    public List<StudentProfileDtoList> getAllStudentProfiles() {
        List<StudentProfile> profileList = studentProfileRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return profileList.stream()
                .map(sp -> new StudentProfileDtoList(sp.getId(), sp.getFirstName(), sp.getLastName()))
>>>>>>> daccd45 (Initial commit)
                .collect(Collectors.toList());
    }

    public Page<StudentProfileDto> getAllStudentProfilesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
<<<<<<< HEAD
        Page<StudentProfile> page = studentProfileRepository.findAll(pageable);

        List<StudentProfileDto> dtoList = page.getContent().stream()
                .map(CommonBuilder::buildStudentProfileDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    @Transactional
    public StudentProfile createStudentProfile(StudentProfileDto dto) {
        StudentProfile profile = new StudentProfile();
        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setFatherName(dto.getFatherName());
        profile.setMotherName(dto.getMotherName());

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            profile.setCourse(course);
        }

        if (dto.getCourseSubject() != null && dto.getCourseSubject().getCourseSubjectId() != null) {
            CourseSubject subject = courseSubjectRepository.findByCourseSubjectId(dto.getCourseSubject().getCourseSubjectId());
            profile.setCourseSubject(subject);
        }

        UserProfile createdBy = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        profile.setCreatedBy(createdBy);
        profile.setUpdatedBy(createdBy);

        return studentProfileRepository.save(profile);
    }

    @Transactional
    public StudentProfile updateStudentProfile(StudentProfileDto dto) {
        StudentProfile profile = studentProfileRepository.findById(dto.getId());

        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setFatherName(dto.getFatherName());
        profile.setMotherName(dto.getMotherName());

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            profile.setCourse(course);
        }

        if (dto.getCourseSubject() != null && dto.getCourseSubject().getCourseSubjectId() != null) {
            CourseSubject subject = courseSubjectRepository.findByCourseSubjectId(dto.getCourseSubject().getCourseSubjectId());
            profile.setCourseSubject(subject);
        }

        UserProfile updatedBy = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        profile.setUpdatedBy(updatedBy);

        return studentProfileRepository.save(profile);
=======
        Page<StudentProfile> studentPage = studentProfileRepository.findAll(pageable);
        int totalElements = (int) studentPage.getTotalElements();

        List<StudentProfileDto> dtoList = studentPage.stream()
                .map(sp -> new StudentProfileDto(
                        sp.getId(),
                        sp.getFirstName(),
                        sp.getMiddleName(),
                        sp.getLastName(),
                        sp.getGender(),
                        sp.getFatherName(),
                        sp.getMotherName(),
                        sp
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public StudentProfile createStudentProfile(StudentProfileDto dto) {
        StudentProfile profile = new StudentProfile();
        BeanUtils.copyProperties(dto, profile, "createdBy", "updatedBy", "course", "courseSubject", "bloodGroup", "batch", "academicYear", "country", "religion", "caste", "state", "city");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());

        profile.setCreatedBy(user);
        profile.setUpdatedBy(user);

        profile.setCourse(courseRepository.findById(dto.getCourse().getCourseId()).orElse(null));
        profile.setCourseSubject(courseSubjectRepository.findById(dto.getCourseSubject().getCourseSubjectId()).orElse(null));
        profile.setBloodGroup(bloodGroupRepository.findById(dto.getBloodGroup().getId()));
        profile.setBatch(batchRepository.findById(dto.getBatch().getBatchId()).orElse(null));
        profile.setAcademicYear(academicYearRepository.findById(dto.getAcademicYear().getId()));
        profile.setCountry(countryRepository.findById(dto.getCountry().getCountryId()).orElse(null));
        profile.setReligion(religionRepository.findById(dto.getReligion().getReligionId()).orElse(null));
        profile.setCaste(casteRepository.findById(dto.getCaste().getCasteId()).orElse(null));
        profile.setState(stateRepository.findById(dto.getState().getStateId()).orElse(null));
        profile.setCity(cityRepository.findById(dto.getCity().getCityId()).orElse(null));

        studentProfileRepository.save(profile);

        String fileName = dto.getFileName();
        try {
            if (!StringUtil.checkNullOrEmpty(fileName) &&
                (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
              || fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {

                String fileDirectory = String.valueOf(profile.getId());
                String newFilePath = fileStoreService.moveFile("studentProfile", fileName, fileDirectory);
                logger.info("Student file name : {} and file path : {}", fileName, newFilePath);
                if (!StringUtils.isEmpty(newFilePath)) {
                    profile.setFilePath(newFilePath);
                    profile.setFileName(fileName);
                }
            }
            return studentProfileRepository.save(profile);

        } catch (EntityExistsException e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        }

        return profile;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public StudentProfile updateStudentProfile(StudentProfileDto dto) {
        StudentProfile profile = studentProfileRepository.findById(dto.getId());
        if (profile == null) {
            return null;
        }

        try {
            profile.setFirstName(dto.getFirstName());
            profile.setMiddleName(dto.getMiddleName());
            profile.setLastName(dto.getLastName());
            profile.setGender(dto.getGender());
            profile.setFatherName(dto.getFatherName());
            profile.setMotherName(dto.getMotherName());

            profile.setCourse(courseRepository.findById(dto.getCourse().getCourseId()).orElse(null));
            profile.setCourseSubject(courseSubjectRepository.findById(dto.getCourseSubject().getCourseSubjectId()).orElse(null));
            profile.setBloodGroup(bloodGroupRepository.findById(dto.getBloodGroup().getId()));
            profile.setBatch(batchRepository.findById(dto.getBatch().getBatchId()).orElse(null));
            profile.setAcademicYear(academicYearRepository.findById(dto.getAcademicYear().getId()));
            profile.setCountry(countryRepository.findById(dto.getCountry().getCountryId()).orElse(null));
            profile.setReligion(religionRepository.findById(dto.getReligion().getReligionId()).orElse(null));
            profile.setCaste(casteRepository.findById(dto.getCaste().getCasteId()).orElse(null));
            profile.setState(stateRepository.findById(dto.getState().getStateId()).orElse(null));
            profile.setCity(cityRepository.findById(dto.getCity().getCityId()).orElse(null));
        } catch (Exception e) {
            logger.error("Error while updating StudentProfile with ID {}: {}", dto.getId(), e.getMessage());
        }

        UserProfile updater = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        profile.setUpdatedBy(updater);

        String fileName = dto.getFileName();
        try {
            if (!StringUtil.checkNullOrEmpty(fileName) &&
                (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
              || fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {

                String fileDirectory = String.valueOf(profile.getId());
                String newFilePath = fileStoreService.moveFile("studentProfile", fileName, fileDirectory);
                logger.info("Student file name : {} and file path : {}", fileName, newFilePath);
                if (!StringUtils.isEmpty(newFilePath)) {
                    profile.setFilePath(newFilePath);
                    profile.setFileName(fileName);
                }
            }
            return studentProfileRepository.save(profile);

        } catch (EntityExistsException e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        }

        return profile;
>>>>>>> daccd45 (Initial commit)
    }

    public void deleteStudentProfileById(long id) {
        studentProfileRepository.deleteById(id);
    }
}
