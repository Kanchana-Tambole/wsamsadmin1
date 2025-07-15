package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.State;



	@Repository
	public interface StateRepository  extends JpaRepository<State, Long> {

		State findByStateName(String stateName);

		

		State findByStateId(long stateId);

}

