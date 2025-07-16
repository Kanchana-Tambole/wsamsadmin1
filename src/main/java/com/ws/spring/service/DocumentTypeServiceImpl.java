package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.DocumentTypeDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.DocumentType;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.DocumentTypeRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class DocumentTypeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public DocumentType createDocumentType(DocumentTypeDto dto) {
        DocumentType documentType = new DocumentType();
        BeanUtils.copyProperties(dto, documentType, "createdBy", "updatedBy");

        try {
            UserProfile createdUser = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
            documentType.setCreatedBy(createdUser);
            documentType.setUpdatedBy(createdUser);

            return documentTypeRepository.save(documentType);
        } catch (EntityExistsException e) {
            logger.error("EntityExistsException: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception during createDocumentType: {}", e.getMessage(), e);
        }

        return documentType;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DocumentType updateDocumentType(DocumentTypeDto dto) {
        try {
            DocumentType documentType = documentTypeRepository.findByDocumentTypeId(dto.getDocumentTypeId());
            documentType.setTypeCode(dto.getTypeCode());
            documentType.setDocumentName(dto.getDocumentName());
            documentType.setMandatory(dto.isMandatory());

            UserProfile updatedUser = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
            documentType.setUpdatedBy(updatedUser);

            return documentTypeRepository.save(documentType);
        } catch (EntityExistsException e) {
            logger.error("EntityExistsException: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception during updateDocumentType: {}", e.getMessage(), e);
        }

        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDocumentTypeById(long id) {
        documentTypeRepository.deleteById(id);
    }

    public DocumentTypeDto getDocumentTypeById(long id) {
        DocumentType doc = documentTypeRepository.findByDocumentTypeId(id);
        return CommonBuilder.buildDocumentTypeDto(doc);
    }

    public List<DocumentTypeDto> getAllDocumentTypes() {
        List<DocumentType> list = documentTypeRepository.findAll(Sort.by(Sort.Direction.DESC, "documentTypeId"));
        return CommonBuilder.buildDocumentTypeDtoList(list);
    }

    public Page<DocumentTypeDto> getAllDocumentTypesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("documentTypeId").descending());
        Page<DocumentType> documentPage = documentTypeRepository.findAll(pageable);
        int totalElements = (int) documentPage.getTotalElements();

        return new PageImpl<>(
            documentPage.stream()
                .map(doc -> new DocumentTypeDto(
                    doc.getDocumentTypeId(),
                    doc.getTypeCode(),
                    doc.getDocumentName(),
                    doc.isMandatory(),
                    doc.getInsertedDate(),
                    doc.getUpdatedDate(),
                    doc.getCreatedBy(),
                    doc.getUpdatedBy()))
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    public long getDocumentTypeCount() {
        return documentTypeRepository.count();
    }
}
