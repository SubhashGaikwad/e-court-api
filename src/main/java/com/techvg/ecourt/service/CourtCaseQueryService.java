package com.techvg.ecourt.service;

import com.techvg.ecourt.domain.*; // for static metamodels
import com.techvg.ecourt.domain.CourtCase;
import com.techvg.ecourt.repository.CourtCaseRepository;
import com.techvg.ecourt.service.criteria.CourtCaseCriteria;
import com.techvg.ecourt.service.dto.CourtCaseDTO;
import com.techvg.ecourt.service.mapper.CourtCaseMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link CourtCase} entities in the database.
 * The main input is a {@link CourtCaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CourtCaseDTO} or a {@link Page} of {@link CourtCaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CourtCaseQueryService extends QueryService<CourtCase> {

    private final Logger log = LoggerFactory.getLogger(CourtCaseQueryService.class);

    private final CourtCaseRepository courtCaseRepository;

    private final CourtCaseMapper courtCaseMapper;

    public CourtCaseQueryService(CourtCaseRepository courtCaseRepository, CourtCaseMapper courtCaseMapper) {
        this.courtCaseRepository = courtCaseRepository;
        this.courtCaseMapper = courtCaseMapper;
    }

    /**
     * Return a {@link List} of {@link CourtCaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CourtCaseDTO> findByCriteria(CourtCaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CourtCase> specification = createSpecification(criteria);
        return courtCaseMapper.toDto(courtCaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CourtCaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CourtCaseDTO> findByCriteria(CourtCaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CourtCase> specification = createSpecification(criteria);
        return courtCaseRepository.findAll(specification, page).map(courtCaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CourtCaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CourtCase> specification = createSpecification(criteria);
        return courtCaseRepository.count(specification);
    }

    /**
     * Function to convert {@link CourtCaseCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CourtCase> createSpecification(CourtCaseCriteria criteria) {
        Specification<CourtCase> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CourtCase_.id));
            }
            if (criteria.getCaseNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseNo(), CourtCase_.caseNo));
            }
            if (criteria.getVillageName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVillageName(), CourtCase_.villageName));
            }
            if (criteria.getAccuserName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccuserName(), CourtCase_.accuserName));
            }
            if (criteria.getApplicationNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApplicationNo(), CourtCase_.applicationNo));
            }
            if (criteria.getLandReferenceNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLandReferenceNo(), CourtCase_.landReferenceNo));
            }
            if (criteria.getFirstAppeal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstAppeal(), CourtCase_.firstAppeal));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAmount(), CourtCase_.amount));
            }
            if (criteria.getProjectName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectName(), CourtCase_.projectName));
            }
            if (criteria.getCourtName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCourtName(), CourtCase_.courtName));
            }
            if (criteria.getDefendantName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDefendantName(), CourtCase_.defendantName));
            }
            if (criteria.getCaseDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseDescription(), CourtCase_.caseDescription));
            }
            if (criteria.getCaseFilingDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCaseFilingDate(), CourtCase_.caseFilingDate));
            }
            if (criteria.getTotalClaimAmount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTotalClaimAmount(), CourtCase_.totalClaimAmount));
            }
            if (criteria.getCaseOfficer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseOfficer(), CourtCase_.caseOfficer));
            }
            if (criteria.getCaselawyer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaselawyer(), CourtCase_.caselawyer));
            }
            if (criteria.getNextHearingDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNextHearingDate(), CourtCase_.nextHearingDate));
            }
            if (criteria.getAmountDepositeInCourt() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getAmountDepositeInCourt(), CourtCase_.amountDepositeInCourt));
            }
            if (criteria.getLar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLar(), CourtCase_.lar));
            }
            if (criteria.getIncCompensation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIncCompensation(), CourtCase_.incCompensation));
            }
            if (criteria.getAmountPaidSLO() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAmountPaidSLO(), CourtCase_.amountPaidSLO));
            }
            if (criteria.getChequeNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChequeNo(), CourtCase_.chequeNo));
            }
            if (criteria.getChequeDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChequeDate(), CourtCase_.chequeDate));
            }
            if (criteria.getAppealNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAppealNo(), CourtCase_.appealNo));
            }
            if (criteria.getCourtAmount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCourtAmount(), CourtCase_.courtAmount));
            }
            if (criteria.getAppealAmount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAppealAmount(), CourtCase_.appealAmount));
            }
            if (criteria.getAppealDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAppealDate(), CourtCase_.appealDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CourtCase_.description));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), CourtCase_.comment));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), CourtCase_.status));
            }
            if (criteria.getFreefield1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreefield1(), CourtCase_.freefield1));
            }
            if (criteria.getFreefield2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreefield2(), CourtCase_.freefield2));
            }
            if (criteria.getFreefield3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreefield3(), CourtCase_.freefield3));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), CourtCase_.lastModifiedBy));
            }
            if (criteria.getLastModified() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModified(), CourtCase_.lastModified));
            }
            if (criteria.getHearingId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHearingId(), root -> root.join(CourtCase_.hearings, JoinType.LEFT).get(Hearing_.id))
                    );
            }
        }
        return specification;
    }
}
