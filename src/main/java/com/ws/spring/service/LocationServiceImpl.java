package com.ws.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.LocationDto;
import com.ws.spring.model.Location;
import com.ws.spring.repository.LocationRepository;

@Service
public class LocationServiceImpl {
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	LocationRepository locationRepository;
	
	public Location createLocation(LocationDto locationDto) {
		Location location = new Location();
		BeanUtils.copyProperties(locationDto, location);
		return locationRepository.save(location);
	}

	public Location updateLocation(LocationDto locationDto) {
		Location location = locationRepository.findByLocationId(locationDto.getLocationId());
		BeanUtils.copyProperties(locationDto, location);
		return locationRepository.save(location);
	}

	public LocationDto queryLocationByLocationId(long locationId) {
		Location location = locationRepository.findByLocationId(locationId);
		return CommonBuilder.buildLocationDto(location);
	}

	public List<LocationDto> queryAllLocations() {
		List<Location> locationList = locationRepository
                .findAll(Sort.by(Sort.Direction.DESC, "insertedDate"));
        return CommonBuilder.buildLocationDtoList(locationList);
	}

	public void deleteLocationById(long locationId) {
		locationRepository.deleteById(locationId);
		
	}

}
