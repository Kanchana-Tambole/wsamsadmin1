package com.ws.spring.service;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> daccd45 (Initial commit)
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
=======

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

>>>>>>> daccd45 (Initial commit)
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
<<<<<<< HEAD
=======

>>>>>>> daccd45 (Initial commit)
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.StateDto;
<<<<<<< HEAD
import com.ws.spring.model.State;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.StateRepository;
import com.ws.spring.repository.UserProfileRepository;


@Service
public class StateServiceImpl {
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public State createState(StateDto stateDto) {
	
		  State state = new State();
		  BeanUtils.copyProperties(stateDto, state,"createdBy","updatedBy");
		
		 UserProfile userProfile = userProfileRepository.findByUserId(stateDto.getCreatedBy().getUserId());
		 state.setCreatedBy(userProfile);
		 state.setUpdatedBy(userProfile);
				
		 return stateRepository.save(state);
			
	}



	public State getStateNameExist(String stateName) {
		return stateRepository.findByStateName(stateName);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public State updateState(StateDto stateDto) {
		com.ws.spring.model.State state = stateRepository.findByStateId(stateDto.getStateId());
	
		try {
			state.setStateName(stateDto.getStateName());
			state.setDescription(stateDto.getDescription());
				
		} catch (Exception e) {
			logger.error(" Error while updating State {} and the Error is : {}", stateDto.getStateName(),
					e.getMessage());
		}
		
		UserProfile userProfile = userProfileRepository.findByUserId(stateDto.getUpdatedBy().getUserId());
		state.setUpdatedBy(userProfile);

		
		return stateRepository.save(state);
	}


	public StateDto getStateByStateId(long stateId) {
		State state = stateRepository.findByStateId(stateId);
        return CommonBuilder.buildStateDto(state);
	}



	public Page<StateDto> getAllStateByPagination(int pageNumber, int pageSize) {
		
		//Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("stateId").descending());
		
		Page<State> statePage = stateRepository.findAll(pageable);
		
        int totalElements = (int) statePage.getTotalElements();
        return new PageImpl<StateDto>(statePage
                .stream()
                .map(state -> new StateDto(
                		state.getStateId(),
                		state.getStateName(), state.getDescription(), state.getInsertedDate(),state.getUpdatedDate(),  state.getCreatedBy(), state.getUpdatedBy()))
                     
                .collect(Collectors.toList()), pageable, totalElements);
		
	}



	public void deleteStateById(long stateId) {
		stateRepository.deleteById(stateId);
		
	}



	

}

=======
import com.ws.spring.dto.StateDtoList;
import com.ws.spring.model.Country;
import com.ws.spring.model.State;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.CountryRepository;
import com.ws.spring.repository.StateRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class StateServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CountryRepository countryRepository;

    public StateDto getStateByStateId(long stateId) {
        State state = stateRepository.findByStateId(stateId);
        return CommonBuilder.buildStateDto(state, true);
    }

    public List<StateDtoList> getAllState() {
        List<State> stateList = stateRepository.findAll(Sort.by(Sort.Direction.DESC, "stateId"));
        return CommonBuilder.buildStateDtoList(stateList);
    }

    public Page<StateDto> getAllStateByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("stateId").descending());
        Page<State> statePage = stateRepository.findAll(pageable);

        int totalElements = (int) statePage.getTotalElements();

        return new PageImpl<>(
            statePage.stream()
                .map(state -> CommonBuilder.buildStateDto(state, true))
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public State createState(StateDto stateDto) {
        State state = new State();
        BeanUtils.copyProperties(stateDto, state, "createdBy", "updatedBy", "country");

        // ✅ Set created/updated by user
        UserProfile userProfile = userProfileRepository.findByUserId(stateDto.getCreatedBy().getUserId());
        state.setCreatedBy(userProfile);
        state.setUpdatedBy(userProfile);

        // ✅ Set associated Country
        if (stateDto.getCountry() != null && stateDto.getCountry().getCountryId() > 0) {
            Country country = countryRepository.findByCountryId(stateDto.getCountry().getCountryId());
            state.setCountry(country);
        }

        return stateRepository.save(state);
    }

    public State getStateNameExist(String stateName) {
        return stateRepository.findByStateName(stateName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public State updateState(StateDto stateDto) {
        State state = stateRepository.findByStateId(stateDto.getStateId());

        try {
            state.setStateName(stateDto.getStateName());
            state.setDescription(stateDto.getDescription());

            // ✅ Update associated Country
            if (stateDto.getCountry() != null && stateDto.getCountry().getCountryId() > 0) {
                Country country = countryRepository.findByCountryId(stateDto.getCountry().getCountryId());
                state.setCountry(country);
            }

        } catch (Exception e) {
            logger.error("Error while updating State {} and the Error is : {}", stateDto.getStateName(), e.getMessage());
        }

        UserProfile userProfile = userProfileRepository.findByUserId(stateDto.getUpdatedBy().getUserId());
        state.setUpdatedBy(userProfile);

        return stateRepository.save(state);
    }

    public void deleteStateById(long stateId) {
        stateRepository.deleteById(stateId);
    }
}
>>>>>>> daccd45 (Initial commit)
