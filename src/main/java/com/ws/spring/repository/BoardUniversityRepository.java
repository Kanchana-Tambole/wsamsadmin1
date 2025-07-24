package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.BoardUniversity;

@Repository
public interface BoardUniversityRepository extends JpaRepository<BoardUniversity, Long> {

    BoardUniversity findById(long id);

    BoardUniversity findByName(String name);
}
