package com.techvg.ecourt.service;

import com.techvg.ecourt.domain.Hearing;
import com.techvg.ecourt.repository.HearingRepository;
import com.techvg.ecourt.service.dto.HearingDTO;
import com.techvg.ecourt.service.mapper.HearingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Hearing}.
 */
@Service
@Transactional
public class HearingService {

    private final Logger log = LoggerFactory.getLogger(HearingService.class);

    private final HearingRepository hearingRepository;

    private final HearingMapper hearingMapper;

    public HearingService(HearingRepository hearingRepository, HearingMapper hearingMapper) {
        this.hearingRepository = hearingRepository;
        this.hearingMapper = hearingMapper;
    }

    /**
     * Save a hearing.
     *
     * @param hearingDTO the entity to save.
     * @return the persisted entity.
     */
    public HearingDTO save(HearingDTO hearingDTO) {
        log.debug("Request to save Hearing : {}", hearingDTO);
        Hearing hearing = hearingMapper.toEntity(hearingDTO);
        hearing = hearingRepository.save(hearing);
        return hearingMapper.toDto(hearing);
    }

    /**
     * Partially update a hearing.
     *
     * @param hearingDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HearingDTO> partialUpdate(HearingDTO hearingDTO) {
        log.debug("Request to partially update Hearing : {}", hearingDTO);

        return hearingRepository
            .findById(hearingDTO.getId())
            .map(existingHearing -> {
                hearingMapper.partialUpdate(existingHearing, hearingDTO);

                return existingHearing;
            })
            .map(hearingRepository::save)
            .map(hearingMapper::toDto);
    }

    /**
     * Get all the hearings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HearingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Hearings");
        return hearingRepository.findAll(pageable).map(hearingMapper::toDto);
    }

    /**
     * Get one hearing by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HearingDTO> findOne(Long id) {
        log.debug("Request to get Hearing : {}", id);
        return hearingRepository.findById(id).map(hearingMapper::toDto);
    }

    /**
     * Delete the hearing by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Hearing : {}", id);
        hearingRepository.deleteById(id);
    }
}
