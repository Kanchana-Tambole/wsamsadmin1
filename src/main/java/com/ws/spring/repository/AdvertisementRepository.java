package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    Advertisement findByAdvertisementId(long advertisementId);

}
