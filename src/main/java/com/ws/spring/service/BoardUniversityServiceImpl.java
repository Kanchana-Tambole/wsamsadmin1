package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

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

import com.ws.spring.dto.BoardUniversityDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.BoardUniversity;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.BoardUniversityRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class BoardUniversityServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BoardUniversityRepository boardUniversityRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public BoardUniversityDto getBoardUniversityById(long id) {
        BoardUniversity bu = boardUniversityRepository.findById(id);
        return CommonBuilder.buildBoardUniversityDto(bu);
    }

    public List<BoardUniversityDto> getAllBoardUniversities() {
        List<BoardUniversity> list = boardUniversityRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream()
                .map(CommonBuilder::buildBoardUniversityDto)
                .collect(Collectors.toList());
    }

    public Page<BoardUniversityDto> getAllBoardUniversitiesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<BoardUniversity> page = boardUniversityRepository.findAll(pageable);
        int totalElements = (int) page.getTotalElements();

        return new PageImpl<>(
                page.stream()
                        .map(CommonBuilder::buildBoardUniversityDto)
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BoardUniversity createBoardUniversity(BoardUniversityDto dto) {
        BoardUniversity bu = new BoardUniversity();
        BeanUtils.copyProperties(dto, bu, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        bu.setCreatedBy(user);
        bu.setUpdatedBy(user);

        return boardUniversityRepository.save(bu);
    }

    public BoardUniversity getBoardUniversityByName(String name) {
        return boardUniversityRepository.findByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BoardUniversity updateBoardUniversity(BoardUniversityDto dto) {
        BoardUniversity bu = boardUniversityRepository.findById(dto.getId()).orElseThrow(null);

        try {
            bu.setName(dto.getName());
            bu.setType(dto.getType());
        } catch (Exception e) {
            logger.error("Error while updating BoardUniversity {}: {}", dto.getName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        bu.setUpdatedBy(user);

        return boardUniversityRepository.save(bu);
    }

    public void deleteBoardUniversityById(long id) {
        boardUniversityRepository.deleteById(id);
    }
}
