package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.City;




	@Repository
	public interface CityRepository  extends JpaRepository<City, Long> {

		City findByCityName(String cityName);

		

		City findByCityId(long cityId);

}

