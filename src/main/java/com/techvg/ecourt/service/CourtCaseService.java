package com.techvg.ecourt.service;

import com.techvg.ecourt.domain.CourtCase;
import com.techvg.ecourt.repository.CourtCaseRepository;
import com.techvg.ecourt.service.dto.CourtCaseDTO;
import com.techvg.ecourt.service.mapper.CourtCaseMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CourtCase}.
 */
@Service
@Transactional
public class CourtCaseService {

    private final Logger log = LoggerFactory.getLogger(CourtCaseService.class);

    private final CourtCaseRepository courtCaseRepository;

    private final CourtCaseMapper courtCaseMapper;

    public CourtCaseService(CourtCaseRepository courtCaseRepository, CourtCaseMapper courtCaseMapper) {
        this.courtCaseRepository = courtCaseRepository;
        this.courtCaseMapper = courtCaseMapper;
    }

    /**
     * Save a courtCase.
     *
     * @param courtCaseDTO the entity to save.
     * @return the persisted entity.
     */
    public CourtCaseDTO save(CourtCaseDTO courtCaseDTO) {
        log.debug("Request to save CourtCase : {}", courtCaseDTO);
        CourtCase courtCase = courtCaseMapper.toEntity(courtCaseDTO);
        courtCase = courtCaseRepository.save(courtCase);
        return courtCaseMapper.toDto(courtCase);
    }

    /**
     * Partially update a courtCase.
     *
     * @param courtCaseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CourtCaseDTO> partialUpdate(CourtCaseDTO courtCaseDTO) {
        log.debug("Request to partially update CourtCase : {}", courtCaseDTO);

        return courtCaseRepository
            .findById(courtCaseDTO.getId())
            .map(existingCourtCase -> {
                courtCaseMapper.partialUpdate(existingCourtCase, courtCaseDTO);

                return existingCourtCase;
            })
            .map(courtCaseRepository::save)
            .map(courtCaseMapper::toDto);
    }

    /**
     * Get all the courtCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CourtCaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CourtCases");
        return courtCaseRepository.findAll(pageable).map(courtCaseMapper::toDto);
    }

    /**
     * Get one courtCase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CourtCaseDTO> findOne(Long id) {
        log.debug("Request to get CourtCase : {}", id);
        return courtCaseRepository.findById(id).map(courtCaseMapper::toDto);
    }

    /**
     * Delete the courtCase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CourtCase : {}", id);
        courtCaseRepository.deleteById(id);
    }
}
