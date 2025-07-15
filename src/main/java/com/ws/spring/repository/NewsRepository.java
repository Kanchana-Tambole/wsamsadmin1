package com.ws.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{

	News findByNewsId(long newsId);

	Page<News> findAllByIsDeleteIsFalse(Pageable pageable);

}
