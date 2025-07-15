package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Occupation;


	@Repository
	public interface OccupationRepository  extends JpaRepository<Occupation, Long> {

		Occupation findByOccupationName(String occupationName);

		

		


		Occupation findByOccupationId(long occupationId);

}

