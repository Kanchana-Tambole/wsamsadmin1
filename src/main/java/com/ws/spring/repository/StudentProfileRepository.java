package com.ws.spring.repository;

import com.ws.spring.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    StudentProfile findById(long id);

    StudentProfile findByFirstName(String firstName);

}
