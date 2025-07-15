package com.ws.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.SuccessStory;

@Repository
public interface SuccessStoryRepository extends JpaRepository<SuccessStory, Long>{

	SuccessStory findBySuccessstoryId(long successstoryId);

	Page<SuccessStory> findAllByIsDeleteIsFalse(Pageable pageable);

}
