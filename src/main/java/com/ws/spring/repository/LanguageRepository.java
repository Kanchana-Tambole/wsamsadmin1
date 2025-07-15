package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>{

	Language findByLanguageName(String languageName);

	Language findByLanguageId(long languageId);

	

}
