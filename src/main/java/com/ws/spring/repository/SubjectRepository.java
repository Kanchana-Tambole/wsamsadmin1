package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findById(long id);

    Subject findBySubjectName(String subjectName);

    Subject findBySubjectCode(String subjectCode); // Optional, based on your model
}
