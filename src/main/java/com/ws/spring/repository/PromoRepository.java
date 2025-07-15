package com.ws.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long>{

	Promo findByPromoId(long promoId);

	Page<Promo> findAllByIsDeleteIsFalse(Pageable pageable);

	long countByIsDeleteIsFalse();

	
}
