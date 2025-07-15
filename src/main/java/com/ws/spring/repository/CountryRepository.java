package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Country;



	@Repository
	public interface CountryRepository  extends JpaRepository<Country, Long> {

		Country findByCountryName(String countryName);

		

		Country findByCountryId(long countryId);
}