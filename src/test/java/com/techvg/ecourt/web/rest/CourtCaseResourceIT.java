package com.techvg.ecourt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.techvg.ecourt.IntegrationTest;
import com.techvg.ecourt.domain.CourtCase;
import com.techvg.ecourt.domain.Hearing;
import com.techvg.ecourt.domain.enumeration.Status;
import com.techvg.ecourt.repository.CourtCaseRepository;
import com.techvg.ecourt.service.criteria.CourtCaseCriteria;
import com.techvg.ecourt.service.dto.CourtCaseDTO;
import com.techvg.ecourt.service.mapper.CourtCaseMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CourtCaseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CourtCaseResourceIT {

    private static final String DEFAULT_CASE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CASE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCUSER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCUSER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATION_NO = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION_NO = "BBBBBBBBBB";

    private static final String DEFAULT_LAND_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_LAND_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_APPEAL = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_APPEAL = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COURT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COURT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEFENDANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEFENDANT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CASE_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_CASE_FILING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CASE_FILING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TOTAL_CLAIM_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_CLAIM_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_OFFICER = "AAAAAAAAAA";
    private static final String UPDATED_CASE_OFFICER = "BBBBBBBBBB";

    private static final String DEFAULT_CASELAWYER = "AAAAAAAAAA";
    private static final String UPDATED_CASELAWYER = "BBBBBBBBBB";

    private static final Instant DEFAULT_NEXT_HEARING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NEXT_HEARING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_AMOUNT_DEPOSITE_IN_COURT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_DEPOSITE_IN_COURT = "BBBBBBBBBB";

    private static final String DEFAULT_LAR = "AAAAAAAAAA";
    private static final String UPDATED_LAR = "BBBBBBBBBB";

    private static final String DEFAULT_INC_COMPENSATION = "AAAAAAAAAA";
    private static final String UPDATED_INC_COMPENSATION = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT_PAID_SLO = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_PAID_SLO = "BBBBBBBBBB";

    private static final String DEFAULT_CHEQUE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHEQUE_NO = "BBBBBBBBBB";

    private static final Instant DEFAULT_CHEQUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CHEQUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_APPEAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_APPEAL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_COURT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_COURT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_APPEAL_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_APPEAL_AMOUNT = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPEAL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPEAL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.OPEN;
    private static final Status UPDATED_STATUS = Status.HEARING;

    private static final String DEFAULT_FREEFIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREEFIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREEFIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREEFIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREEFIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREEFIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/court-cases";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CourtCaseRepository courtCaseRepository;

    @Autowired
    private CourtCaseMapper courtCaseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCourtCaseMockMvc;

    private CourtCase courtCase;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourtCase createEntity(EntityManager em) {
        CourtCase courtCase = new CourtCase()
            .caseNo(DEFAULT_CASE_NO)
            .villageName(DEFAULT_VILLAGE_NAME)
            .accuserName(DEFAULT_ACCUSER_NAME)
            .applicationNo(DEFAULT_APPLICATION_NO)
            .landReferenceNo(DEFAULT_LAND_REFERENCE_NO)
            .firstAppeal(DEFAULT_FIRST_APPEAL)
            .amount(DEFAULT_AMOUNT)
            .projectName(DEFAULT_PROJECT_NAME)
            .courtName(DEFAULT_COURT_NAME)
            .defendantName(DEFAULT_DEFENDANT_NAME)
            .caseDescription(DEFAULT_CASE_DESCRIPTION)
            .caseFilingDate(DEFAULT_CASE_FILING_DATE)
            .totalClaimAmount(DEFAULT_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(DEFAULT_CASE_OFFICER)
            .caselawyer(DEFAULT_CASELAWYER)
            .nextHearingDate(DEFAULT_NEXT_HEARING_DATE)
            .amountDepositeInCourt(DEFAULT_AMOUNT_DEPOSITE_IN_COURT)
            .lar(DEFAULT_LAR)
            .incCompensation(DEFAULT_INC_COMPENSATION)
            .amountPaidSLO(DEFAULT_AMOUNT_PAID_SLO)
            .chequeNo(DEFAULT_CHEQUE_NO)
            .chequeDate(DEFAULT_CHEQUE_DATE)
            .appealNo(DEFAULT_APPEAL_NO)
            .courtAmount(DEFAULT_COURT_AMOUNT)
            .appealAmount(DEFAULT_APPEAL_AMOUNT)
            .appealDate(DEFAULT_APPEAL_DATE)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .status(DEFAULT_STATUS)
            .freefield1(DEFAULT_FREEFIELD_1)
            .freefield2(DEFAULT_FREEFIELD_2)
            .freefield3(DEFAULT_FREEFIELD_3)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModified(DEFAULT_LAST_MODIFIED);
        return courtCase;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourtCase createUpdatedEntity(EntityManager em) {
        CourtCase courtCase = new CourtCase()
            .caseNo(UPDATED_CASE_NO)
            .villageName(UPDATED_VILLAGE_NAME)
            .accuserName(UPDATED_ACCUSER_NAME)
            .applicationNo(UPDATED_APPLICATION_NO)
            .landReferenceNo(UPDATED_LAND_REFERENCE_NO)
            .firstAppeal(UPDATED_FIRST_APPEAL)
            .amount(UPDATED_AMOUNT)
            .projectName(UPDATED_PROJECT_NAME)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .totalClaimAmount(UPDATED_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(UPDATED_CASE_OFFICER)
            .caselawyer(UPDATED_CASELAWYER)
            .nextHearingDate(UPDATED_NEXT_HEARING_DATE)
            .amountDepositeInCourt(UPDATED_AMOUNT_DEPOSITE_IN_COURT)
            .lar(UPDATED_LAR)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .chequeDate(UPDATED_CHEQUE_DATE)
            .appealNo(UPDATED_APPEAL_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .appealDate(UPDATED_APPEAL_DATE)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield2(UPDATED_FREEFIELD_2)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModified(UPDATED_LAST_MODIFIED);
        return courtCase;
    }

    @BeforeEach
    public void initTest() {
        courtCase = createEntity(em);
    }

    @Test
    @Transactional
    void createCourtCase() throws Exception {
        int databaseSizeBeforeCreate = courtCaseRepository.findAll().size();
        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);
        restCourtCaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courtCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeCreate + 1);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(DEFAULT_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(DEFAULT_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(DEFAULT_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(DEFAULT_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(DEFAULT_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(DEFAULT_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(DEFAULT_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(DEFAULT_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(DEFAULT_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(DEFAULT_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(DEFAULT_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(DEFAULT_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(DEFAULT_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(DEFAULT_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(DEFAULT_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(DEFAULT_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(DEFAULT_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(DEFAULT_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(DEFAULT_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(DEFAULT_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(DEFAULT_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(DEFAULT_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(DEFAULT_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testCourtCase.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(DEFAULT_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(DEFAULT_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(DEFAULT_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void createCourtCaseWithExistingId() throws Exception {
        // Create the CourtCase with an existing ID
        courtCase.setId(1L);
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        int databaseSizeBeforeCreate = courtCaseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourtCaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courtCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCourtCases() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courtCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseNo").value(hasItem(DEFAULT_CASE_NO)))
            .andExpect(jsonPath("$.[*].villageName").value(hasItem(DEFAULT_VILLAGE_NAME)))
            .andExpect(jsonPath("$.[*].accuserName").value(hasItem(DEFAULT_ACCUSER_NAME)))
            .andExpect(jsonPath("$.[*].applicationNo").value(hasItem(DEFAULT_APPLICATION_NO)))
            .andExpect(jsonPath("$.[*].landReferenceNo").value(hasItem(DEFAULT_LAND_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].firstAppeal").value(hasItem(DEFAULT_FIRST_APPEAL)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME)))
            .andExpect(jsonPath("$.[*].courtName").value(hasItem(DEFAULT_COURT_NAME)))
            .andExpect(jsonPath("$.[*].defendantName").value(hasItem(DEFAULT_DEFENDANT_NAME)))
            .andExpect(jsonPath("$.[*].caseDescription").value(hasItem(DEFAULT_CASE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].caseFilingDate").value(hasItem(DEFAULT_CASE_FILING_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalClaimAmount").value(hasItem(DEFAULT_TOTAL_CLAIM_AMOUNT)))
            .andExpect(jsonPath("$.[*].caseOfficer").value(hasItem(DEFAULT_CASE_OFFICER)))
            .andExpect(jsonPath("$.[*].caselawyer").value(hasItem(DEFAULT_CASELAWYER)))
            .andExpect(jsonPath("$.[*].nextHearingDate").value(hasItem(DEFAULT_NEXT_HEARING_DATE.toString())))
            .andExpect(jsonPath("$.[*].amountDepositeInCourt").value(hasItem(DEFAULT_AMOUNT_DEPOSITE_IN_COURT)))
            .andExpect(jsonPath("$.[*].lar").value(hasItem(DEFAULT_LAR)))
            .andExpect(jsonPath("$.[*].incCompensation").value(hasItem(DEFAULT_INC_COMPENSATION)))
            .andExpect(jsonPath("$.[*].amountPaidSLO").value(hasItem(DEFAULT_AMOUNT_PAID_SLO)))
            .andExpect(jsonPath("$.[*].chequeNo").value(hasItem(DEFAULT_CHEQUE_NO)))
            .andExpect(jsonPath("$.[*].chequeDate").value(hasItem(DEFAULT_CHEQUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].appealNo").value(hasItem(DEFAULT_APPEAL_NO)))
            .andExpect(jsonPath("$.[*].courtAmount").value(hasItem(DEFAULT_COURT_AMOUNT)))
            .andExpect(jsonPath("$.[*].appealAmount").value(hasItem(DEFAULT_APPEAL_AMOUNT)))
            .andExpect(jsonPath("$.[*].appealDate").value(hasItem(DEFAULT_APPEAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].freefield1").value(hasItem(DEFAULT_FREEFIELD_1)))
            .andExpect(jsonPath("$.[*].freefield2").value(hasItem(DEFAULT_FREEFIELD_2)))
            .andExpect(jsonPath("$.[*].freefield3").value(hasItem(DEFAULT_FREEFIELD_3)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)));
    }

    @Test
    @Transactional
    void getCourtCase() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get the courtCase
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL_ID, courtCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(courtCase.getId().intValue()))
            .andExpect(jsonPath("$.caseNo").value(DEFAULT_CASE_NO))
            .andExpect(jsonPath("$.villageName").value(DEFAULT_VILLAGE_NAME))
            .andExpect(jsonPath("$.accuserName").value(DEFAULT_ACCUSER_NAME))
            .andExpect(jsonPath("$.applicationNo").value(DEFAULT_APPLICATION_NO))
            .andExpect(jsonPath("$.landReferenceNo").value(DEFAULT_LAND_REFERENCE_NO))
            .andExpect(jsonPath("$.firstAppeal").value(DEFAULT_FIRST_APPEAL))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.projectName").value(DEFAULT_PROJECT_NAME))
            .andExpect(jsonPath("$.courtName").value(DEFAULT_COURT_NAME))
            .andExpect(jsonPath("$.defendantName").value(DEFAULT_DEFENDANT_NAME))
            .andExpect(jsonPath("$.caseDescription").value(DEFAULT_CASE_DESCRIPTION))
            .andExpect(jsonPath("$.caseFilingDate").value(DEFAULT_CASE_FILING_DATE.toString()))
            .andExpect(jsonPath("$.totalClaimAmount").value(DEFAULT_TOTAL_CLAIM_AMOUNT))
            .andExpect(jsonPath("$.caseOfficer").value(DEFAULT_CASE_OFFICER))
            .andExpect(jsonPath("$.caselawyer").value(DEFAULT_CASELAWYER))
            .andExpect(jsonPath("$.nextHearingDate").value(DEFAULT_NEXT_HEARING_DATE.toString()))
            .andExpect(jsonPath("$.amountDepositeInCourt").value(DEFAULT_AMOUNT_DEPOSITE_IN_COURT))
            .andExpect(jsonPath("$.lar").value(DEFAULT_LAR))
            .andExpect(jsonPath("$.incCompensation").value(DEFAULT_INC_COMPENSATION))
            .andExpect(jsonPath("$.amountPaidSLO").value(DEFAULT_AMOUNT_PAID_SLO))
            .andExpect(jsonPath("$.chequeNo").value(DEFAULT_CHEQUE_NO))
            .andExpect(jsonPath("$.chequeDate").value(DEFAULT_CHEQUE_DATE.toString()))
            .andExpect(jsonPath("$.appealNo").value(DEFAULT_APPEAL_NO))
            .andExpect(jsonPath("$.courtAmount").value(DEFAULT_COURT_AMOUNT))
            .andExpect(jsonPath("$.appealAmount").value(DEFAULT_APPEAL_AMOUNT))
            .andExpect(jsonPath("$.appealDate").value(DEFAULT_APPEAL_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.freefield1").value(DEFAULT_FREEFIELD_1))
            .andExpect(jsonPath("$.freefield2").value(DEFAULT_FREEFIELD_2))
            .andExpect(jsonPath("$.freefield3").value(DEFAULT_FREEFIELD_3))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED));
    }

    @Test
    @Transactional
    void getCourtCasesByIdFiltering() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        Long id = courtCase.getId();

        defaultCourtCaseShouldBeFound("id.equals=" + id);
        defaultCourtCaseShouldNotBeFound("id.notEquals=" + id);

        defaultCourtCaseShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCourtCaseShouldNotBeFound("id.greaterThan=" + id);

        defaultCourtCaseShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCourtCaseShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseNoIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseNo equals to DEFAULT_CASE_NO
        defaultCourtCaseShouldBeFound("caseNo.equals=" + DEFAULT_CASE_NO);

        // Get all the courtCaseList where caseNo equals to UPDATED_CASE_NO
        defaultCourtCaseShouldNotBeFound("caseNo.equals=" + UPDATED_CASE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseNo not equals to DEFAULT_CASE_NO
        defaultCourtCaseShouldNotBeFound("caseNo.notEquals=" + DEFAULT_CASE_NO);

        // Get all the courtCaseList where caseNo not equals to UPDATED_CASE_NO
        defaultCourtCaseShouldBeFound("caseNo.notEquals=" + UPDATED_CASE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseNoIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseNo in DEFAULT_CASE_NO or UPDATED_CASE_NO
        defaultCourtCaseShouldBeFound("caseNo.in=" + DEFAULT_CASE_NO + "," + UPDATED_CASE_NO);

        // Get all the courtCaseList where caseNo equals to UPDATED_CASE_NO
        defaultCourtCaseShouldNotBeFound("caseNo.in=" + UPDATED_CASE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseNo is not null
        defaultCourtCaseShouldBeFound("caseNo.specified=true");

        // Get all the courtCaseList where caseNo is null
        defaultCourtCaseShouldNotBeFound("caseNo.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseNoContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseNo contains DEFAULT_CASE_NO
        defaultCourtCaseShouldBeFound("caseNo.contains=" + DEFAULT_CASE_NO);

        // Get all the courtCaseList where caseNo contains UPDATED_CASE_NO
        defaultCourtCaseShouldNotBeFound("caseNo.contains=" + UPDATED_CASE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseNoNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseNo does not contain DEFAULT_CASE_NO
        defaultCourtCaseShouldNotBeFound("caseNo.doesNotContain=" + DEFAULT_CASE_NO);

        // Get all the courtCaseList where caseNo does not contain UPDATED_CASE_NO
        defaultCourtCaseShouldBeFound("caseNo.doesNotContain=" + UPDATED_CASE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByVillageNameIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where villageName equals to DEFAULT_VILLAGE_NAME
        defaultCourtCaseShouldBeFound("villageName.equals=" + DEFAULT_VILLAGE_NAME);

        // Get all the courtCaseList where villageName equals to UPDATED_VILLAGE_NAME
        defaultCourtCaseShouldNotBeFound("villageName.equals=" + UPDATED_VILLAGE_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByVillageNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where villageName not equals to DEFAULT_VILLAGE_NAME
        defaultCourtCaseShouldNotBeFound("villageName.notEquals=" + DEFAULT_VILLAGE_NAME);

        // Get all the courtCaseList where villageName not equals to UPDATED_VILLAGE_NAME
        defaultCourtCaseShouldBeFound("villageName.notEquals=" + UPDATED_VILLAGE_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByVillageNameIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where villageName in DEFAULT_VILLAGE_NAME or UPDATED_VILLAGE_NAME
        defaultCourtCaseShouldBeFound("villageName.in=" + DEFAULT_VILLAGE_NAME + "," + UPDATED_VILLAGE_NAME);

        // Get all the courtCaseList where villageName equals to UPDATED_VILLAGE_NAME
        defaultCourtCaseShouldNotBeFound("villageName.in=" + UPDATED_VILLAGE_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByVillageNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where villageName is not null
        defaultCourtCaseShouldBeFound("villageName.specified=true");

        // Get all the courtCaseList where villageName is null
        defaultCourtCaseShouldNotBeFound("villageName.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByVillageNameContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where villageName contains DEFAULT_VILLAGE_NAME
        defaultCourtCaseShouldBeFound("villageName.contains=" + DEFAULT_VILLAGE_NAME);

        // Get all the courtCaseList where villageName contains UPDATED_VILLAGE_NAME
        defaultCourtCaseShouldNotBeFound("villageName.contains=" + UPDATED_VILLAGE_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByVillageNameNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where villageName does not contain DEFAULT_VILLAGE_NAME
        defaultCourtCaseShouldNotBeFound("villageName.doesNotContain=" + DEFAULT_VILLAGE_NAME);

        // Get all the courtCaseList where villageName does not contain UPDATED_VILLAGE_NAME
        defaultCourtCaseShouldBeFound("villageName.doesNotContain=" + UPDATED_VILLAGE_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAccuserNameIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where accuserName equals to DEFAULT_ACCUSER_NAME
        defaultCourtCaseShouldBeFound("accuserName.equals=" + DEFAULT_ACCUSER_NAME);

        // Get all the courtCaseList where accuserName equals to UPDATED_ACCUSER_NAME
        defaultCourtCaseShouldNotBeFound("accuserName.equals=" + UPDATED_ACCUSER_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAccuserNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where accuserName not equals to DEFAULT_ACCUSER_NAME
        defaultCourtCaseShouldNotBeFound("accuserName.notEquals=" + DEFAULT_ACCUSER_NAME);

        // Get all the courtCaseList where accuserName not equals to UPDATED_ACCUSER_NAME
        defaultCourtCaseShouldBeFound("accuserName.notEquals=" + UPDATED_ACCUSER_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAccuserNameIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where accuserName in DEFAULT_ACCUSER_NAME or UPDATED_ACCUSER_NAME
        defaultCourtCaseShouldBeFound("accuserName.in=" + DEFAULT_ACCUSER_NAME + "," + UPDATED_ACCUSER_NAME);

        // Get all the courtCaseList where accuserName equals to UPDATED_ACCUSER_NAME
        defaultCourtCaseShouldNotBeFound("accuserName.in=" + UPDATED_ACCUSER_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAccuserNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where accuserName is not null
        defaultCourtCaseShouldBeFound("accuserName.specified=true");

        // Get all the courtCaseList where accuserName is null
        defaultCourtCaseShouldNotBeFound("accuserName.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAccuserNameContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where accuserName contains DEFAULT_ACCUSER_NAME
        defaultCourtCaseShouldBeFound("accuserName.contains=" + DEFAULT_ACCUSER_NAME);

        // Get all the courtCaseList where accuserName contains UPDATED_ACCUSER_NAME
        defaultCourtCaseShouldNotBeFound("accuserName.contains=" + UPDATED_ACCUSER_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAccuserNameNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where accuserName does not contain DEFAULT_ACCUSER_NAME
        defaultCourtCaseShouldNotBeFound("accuserName.doesNotContain=" + DEFAULT_ACCUSER_NAME);

        // Get all the courtCaseList where accuserName does not contain UPDATED_ACCUSER_NAME
        defaultCourtCaseShouldBeFound("accuserName.doesNotContain=" + UPDATED_ACCUSER_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByApplicationNoIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where applicationNo equals to DEFAULT_APPLICATION_NO
        defaultCourtCaseShouldBeFound("applicationNo.equals=" + DEFAULT_APPLICATION_NO);

        // Get all the courtCaseList where applicationNo equals to UPDATED_APPLICATION_NO
        defaultCourtCaseShouldNotBeFound("applicationNo.equals=" + UPDATED_APPLICATION_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByApplicationNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where applicationNo not equals to DEFAULT_APPLICATION_NO
        defaultCourtCaseShouldNotBeFound("applicationNo.notEquals=" + DEFAULT_APPLICATION_NO);

        // Get all the courtCaseList where applicationNo not equals to UPDATED_APPLICATION_NO
        defaultCourtCaseShouldBeFound("applicationNo.notEquals=" + UPDATED_APPLICATION_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByApplicationNoIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where applicationNo in DEFAULT_APPLICATION_NO or UPDATED_APPLICATION_NO
        defaultCourtCaseShouldBeFound("applicationNo.in=" + DEFAULT_APPLICATION_NO + "," + UPDATED_APPLICATION_NO);

        // Get all the courtCaseList where applicationNo equals to UPDATED_APPLICATION_NO
        defaultCourtCaseShouldNotBeFound("applicationNo.in=" + UPDATED_APPLICATION_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByApplicationNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where applicationNo is not null
        defaultCourtCaseShouldBeFound("applicationNo.specified=true");

        // Get all the courtCaseList where applicationNo is null
        defaultCourtCaseShouldNotBeFound("applicationNo.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByApplicationNoContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where applicationNo contains DEFAULT_APPLICATION_NO
        defaultCourtCaseShouldBeFound("applicationNo.contains=" + DEFAULT_APPLICATION_NO);

        // Get all the courtCaseList where applicationNo contains UPDATED_APPLICATION_NO
        defaultCourtCaseShouldNotBeFound("applicationNo.contains=" + UPDATED_APPLICATION_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByApplicationNoNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where applicationNo does not contain DEFAULT_APPLICATION_NO
        defaultCourtCaseShouldNotBeFound("applicationNo.doesNotContain=" + DEFAULT_APPLICATION_NO);

        // Get all the courtCaseList where applicationNo does not contain UPDATED_APPLICATION_NO
        defaultCourtCaseShouldBeFound("applicationNo.doesNotContain=" + UPDATED_APPLICATION_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLandReferenceNoIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where landReferenceNo equals to DEFAULT_LAND_REFERENCE_NO
        defaultCourtCaseShouldBeFound("landReferenceNo.equals=" + DEFAULT_LAND_REFERENCE_NO);

        // Get all the courtCaseList where landReferenceNo equals to UPDATED_LAND_REFERENCE_NO
        defaultCourtCaseShouldNotBeFound("landReferenceNo.equals=" + UPDATED_LAND_REFERENCE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLandReferenceNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where landReferenceNo not equals to DEFAULT_LAND_REFERENCE_NO
        defaultCourtCaseShouldNotBeFound("landReferenceNo.notEquals=" + DEFAULT_LAND_REFERENCE_NO);

        // Get all the courtCaseList where landReferenceNo not equals to UPDATED_LAND_REFERENCE_NO
        defaultCourtCaseShouldBeFound("landReferenceNo.notEquals=" + UPDATED_LAND_REFERENCE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLandReferenceNoIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where landReferenceNo in DEFAULT_LAND_REFERENCE_NO or UPDATED_LAND_REFERENCE_NO
        defaultCourtCaseShouldBeFound("landReferenceNo.in=" + DEFAULT_LAND_REFERENCE_NO + "," + UPDATED_LAND_REFERENCE_NO);

        // Get all the courtCaseList where landReferenceNo equals to UPDATED_LAND_REFERENCE_NO
        defaultCourtCaseShouldNotBeFound("landReferenceNo.in=" + UPDATED_LAND_REFERENCE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLandReferenceNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where landReferenceNo is not null
        defaultCourtCaseShouldBeFound("landReferenceNo.specified=true");

        // Get all the courtCaseList where landReferenceNo is null
        defaultCourtCaseShouldNotBeFound("landReferenceNo.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByLandReferenceNoContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where landReferenceNo contains DEFAULT_LAND_REFERENCE_NO
        defaultCourtCaseShouldBeFound("landReferenceNo.contains=" + DEFAULT_LAND_REFERENCE_NO);

        // Get all the courtCaseList where landReferenceNo contains UPDATED_LAND_REFERENCE_NO
        defaultCourtCaseShouldNotBeFound("landReferenceNo.contains=" + UPDATED_LAND_REFERENCE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLandReferenceNoNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where landReferenceNo does not contain DEFAULT_LAND_REFERENCE_NO
        defaultCourtCaseShouldNotBeFound("landReferenceNo.doesNotContain=" + DEFAULT_LAND_REFERENCE_NO);

        // Get all the courtCaseList where landReferenceNo does not contain UPDATED_LAND_REFERENCE_NO
        defaultCourtCaseShouldBeFound("landReferenceNo.doesNotContain=" + UPDATED_LAND_REFERENCE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFirstAppealIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where firstAppeal equals to DEFAULT_FIRST_APPEAL
        defaultCourtCaseShouldBeFound("firstAppeal.equals=" + DEFAULT_FIRST_APPEAL);

        // Get all the courtCaseList where firstAppeal equals to UPDATED_FIRST_APPEAL
        defaultCourtCaseShouldNotBeFound("firstAppeal.equals=" + UPDATED_FIRST_APPEAL);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFirstAppealIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where firstAppeal not equals to DEFAULT_FIRST_APPEAL
        defaultCourtCaseShouldNotBeFound("firstAppeal.notEquals=" + DEFAULT_FIRST_APPEAL);

        // Get all the courtCaseList where firstAppeal not equals to UPDATED_FIRST_APPEAL
        defaultCourtCaseShouldBeFound("firstAppeal.notEquals=" + UPDATED_FIRST_APPEAL);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFirstAppealIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where firstAppeal in DEFAULT_FIRST_APPEAL or UPDATED_FIRST_APPEAL
        defaultCourtCaseShouldBeFound("firstAppeal.in=" + DEFAULT_FIRST_APPEAL + "," + UPDATED_FIRST_APPEAL);

        // Get all the courtCaseList where firstAppeal equals to UPDATED_FIRST_APPEAL
        defaultCourtCaseShouldNotBeFound("firstAppeal.in=" + UPDATED_FIRST_APPEAL);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFirstAppealIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where firstAppeal is not null
        defaultCourtCaseShouldBeFound("firstAppeal.specified=true");

        // Get all the courtCaseList where firstAppeal is null
        defaultCourtCaseShouldNotBeFound("firstAppeal.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByFirstAppealContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where firstAppeal contains DEFAULT_FIRST_APPEAL
        defaultCourtCaseShouldBeFound("firstAppeal.contains=" + DEFAULT_FIRST_APPEAL);

        // Get all the courtCaseList where firstAppeal contains UPDATED_FIRST_APPEAL
        defaultCourtCaseShouldNotBeFound("firstAppeal.contains=" + UPDATED_FIRST_APPEAL);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFirstAppealNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where firstAppeal does not contain DEFAULT_FIRST_APPEAL
        defaultCourtCaseShouldNotBeFound("firstAppeal.doesNotContain=" + DEFAULT_FIRST_APPEAL);

        // Get all the courtCaseList where firstAppeal does not contain UPDATED_FIRST_APPEAL
        defaultCourtCaseShouldBeFound("firstAppeal.doesNotContain=" + UPDATED_FIRST_APPEAL);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amount equals to DEFAULT_AMOUNT
        defaultCourtCaseShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the courtCaseList where amount equals to UPDATED_AMOUNT
        defaultCourtCaseShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amount not equals to DEFAULT_AMOUNT
        defaultCourtCaseShouldNotBeFound("amount.notEquals=" + DEFAULT_AMOUNT);

        // Get all the courtCaseList where amount not equals to UPDATED_AMOUNT
        defaultCourtCaseShouldBeFound("amount.notEquals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultCourtCaseShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the courtCaseList where amount equals to UPDATED_AMOUNT
        defaultCourtCaseShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amount is not null
        defaultCourtCaseShouldBeFound("amount.specified=true");

        // Get all the courtCaseList where amount is null
        defaultCourtCaseShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amount contains DEFAULT_AMOUNT
        defaultCourtCaseShouldBeFound("amount.contains=" + DEFAULT_AMOUNT);

        // Get all the courtCaseList where amount contains UPDATED_AMOUNT
        defaultCourtCaseShouldNotBeFound("amount.contains=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amount does not contain DEFAULT_AMOUNT
        defaultCourtCaseShouldNotBeFound("amount.doesNotContain=" + DEFAULT_AMOUNT);

        // Get all the courtCaseList where amount does not contain UPDATED_AMOUNT
        defaultCourtCaseShouldBeFound("amount.doesNotContain=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByProjectNameIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where projectName equals to DEFAULT_PROJECT_NAME
        defaultCourtCaseShouldBeFound("projectName.equals=" + DEFAULT_PROJECT_NAME);

        // Get all the courtCaseList where projectName equals to UPDATED_PROJECT_NAME
        defaultCourtCaseShouldNotBeFound("projectName.equals=" + UPDATED_PROJECT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByProjectNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where projectName not equals to DEFAULT_PROJECT_NAME
        defaultCourtCaseShouldNotBeFound("projectName.notEquals=" + DEFAULT_PROJECT_NAME);

        // Get all the courtCaseList where projectName not equals to UPDATED_PROJECT_NAME
        defaultCourtCaseShouldBeFound("projectName.notEquals=" + UPDATED_PROJECT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByProjectNameIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where projectName in DEFAULT_PROJECT_NAME or UPDATED_PROJECT_NAME
        defaultCourtCaseShouldBeFound("projectName.in=" + DEFAULT_PROJECT_NAME + "," + UPDATED_PROJECT_NAME);

        // Get all the courtCaseList where projectName equals to UPDATED_PROJECT_NAME
        defaultCourtCaseShouldNotBeFound("projectName.in=" + UPDATED_PROJECT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByProjectNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where projectName is not null
        defaultCourtCaseShouldBeFound("projectName.specified=true");

        // Get all the courtCaseList where projectName is null
        defaultCourtCaseShouldNotBeFound("projectName.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByProjectNameContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where projectName contains DEFAULT_PROJECT_NAME
        defaultCourtCaseShouldBeFound("projectName.contains=" + DEFAULT_PROJECT_NAME);

        // Get all the courtCaseList where projectName contains UPDATED_PROJECT_NAME
        defaultCourtCaseShouldNotBeFound("projectName.contains=" + UPDATED_PROJECT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByProjectNameNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where projectName does not contain DEFAULT_PROJECT_NAME
        defaultCourtCaseShouldNotBeFound("projectName.doesNotContain=" + DEFAULT_PROJECT_NAME);

        // Get all the courtCaseList where projectName does not contain UPDATED_PROJECT_NAME
        defaultCourtCaseShouldBeFound("projectName.doesNotContain=" + UPDATED_PROJECT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtNameIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtName equals to DEFAULT_COURT_NAME
        defaultCourtCaseShouldBeFound("courtName.equals=" + DEFAULT_COURT_NAME);

        // Get all the courtCaseList where courtName equals to UPDATED_COURT_NAME
        defaultCourtCaseShouldNotBeFound("courtName.equals=" + UPDATED_COURT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtName not equals to DEFAULT_COURT_NAME
        defaultCourtCaseShouldNotBeFound("courtName.notEquals=" + DEFAULT_COURT_NAME);

        // Get all the courtCaseList where courtName not equals to UPDATED_COURT_NAME
        defaultCourtCaseShouldBeFound("courtName.notEquals=" + UPDATED_COURT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtNameIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtName in DEFAULT_COURT_NAME or UPDATED_COURT_NAME
        defaultCourtCaseShouldBeFound("courtName.in=" + DEFAULT_COURT_NAME + "," + UPDATED_COURT_NAME);

        // Get all the courtCaseList where courtName equals to UPDATED_COURT_NAME
        defaultCourtCaseShouldNotBeFound("courtName.in=" + UPDATED_COURT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtName is not null
        defaultCourtCaseShouldBeFound("courtName.specified=true");

        // Get all the courtCaseList where courtName is null
        defaultCourtCaseShouldNotBeFound("courtName.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtNameContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtName contains DEFAULT_COURT_NAME
        defaultCourtCaseShouldBeFound("courtName.contains=" + DEFAULT_COURT_NAME);

        // Get all the courtCaseList where courtName contains UPDATED_COURT_NAME
        defaultCourtCaseShouldNotBeFound("courtName.contains=" + UPDATED_COURT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtNameNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtName does not contain DEFAULT_COURT_NAME
        defaultCourtCaseShouldNotBeFound("courtName.doesNotContain=" + DEFAULT_COURT_NAME);

        // Get all the courtCaseList where courtName does not contain UPDATED_COURT_NAME
        defaultCourtCaseShouldBeFound("courtName.doesNotContain=" + UPDATED_COURT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDefendantNameIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where defendantName equals to DEFAULT_DEFENDANT_NAME
        defaultCourtCaseShouldBeFound("defendantName.equals=" + DEFAULT_DEFENDANT_NAME);

        // Get all the courtCaseList where defendantName equals to UPDATED_DEFENDANT_NAME
        defaultCourtCaseShouldNotBeFound("defendantName.equals=" + UPDATED_DEFENDANT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDefendantNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where defendantName not equals to DEFAULT_DEFENDANT_NAME
        defaultCourtCaseShouldNotBeFound("defendantName.notEquals=" + DEFAULT_DEFENDANT_NAME);

        // Get all the courtCaseList where defendantName not equals to UPDATED_DEFENDANT_NAME
        defaultCourtCaseShouldBeFound("defendantName.notEquals=" + UPDATED_DEFENDANT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDefendantNameIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where defendantName in DEFAULT_DEFENDANT_NAME or UPDATED_DEFENDANT_NAME
        defaultCourtCaseShouldBeFound("defendantName.in=" + DEFAULT_DEFENDANT_NAME + "," + UPDATED_DEFENDANT_NAME);

        // Get all the courtCaseList where defendantName equals to UPDATED_DEFENDANT_NAME
        defaultCourtCaseShouldNotBeFound("defendantName.in=" + UPDATED_DEFENDANT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDefendantNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where defendantName is not null
        defaultCourtCaseShouldBeFound("defendantName.specified=true");

        // Get all the courtCaseList where defendantName is null
        defaultCourtCaseShouldNotBeFound("defendantName.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByDefendantNameContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where defendantName contains DEFAULT_DEFENDANT_NAME
        defaultCourtCaseShouldBeFound("defendantName.contains=" + DEFAULT_DEFENDANT_NAME);

        // Get all the courtCaseList where defendantName contains UPDATED_DEFENDANT_NAME
        defaultCourtCaseShouldNotBeFound("defendantName.contains=" + UPDATED_DEFENDANT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDefendantNameNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where defendantName does not contain DEFAULT_DEFENDANT_NAME
        defaultCourtCaseShouldNotBeFound("defendantName.doesNotContain=" + DEFAULT_DEFENDANT_NAME);

        // Get all the courtCaseList where defendantName does not contain UPDATED_DEFENDANT_NAME
        defaultCourtCaseShouldBeFound("defendantName.doesNotContain=" + UPDATED_DEFENDANT_NAME);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseDescription equals to DEFAULT_CASE_DESCRIPTION
        defaultCourtCaseShouldBeFound("caseDescription.equals=" + DEFAULT_CASE_DESCRIPTION);

        // Get all the courtCaseList where caseDescription equals to UPDATED_CASE_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("caseDescription.equals=" + UPDATED_CASE_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseDescription not equals to DEFAULT_CASE_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("caseDescription.notEquals=" + DEFAULT_CASE_DESCRIPTION);

        // Get all the courtCaseList where caseDescription not equals to UPDATED_CASE_DESCRIPTION
        defaultCourtCaseShouldBeFound("caseDescription.notEquals=" + UPDATED_CASE_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseDescription in DEFAULT_CASE_DESCRIPTION or UPDATED_CASE_DESCRIPTION
        defaultCourtCaseShouldBeFound("caseDescription.in=" + DEFAULT_CASE_DESCRIPTION + "," + UPDATED_CASE_DESCRIPTION);

        // Get all the courtCaseList where caseDescription equals to UPDATED_CASE_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("caseDescription.in=" + UPDATED_CASE_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseDescription is not null
        defaultCourtCaseShouldBeFound("caseDescription.specified=true");

        // Get all the courtCaseList where caseDescription is null
        defaultCourtCaseShouldNotBeFound("caseDescription.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseDescriptionContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseDescription contains DEFAULT_CASE_DESCRIPTION
        defaultCourtCaseShouldBeFound("caseDescription.contains=" + DEFAULT_CASE_DESCRIPTION);

        // Get all the courtCaseList where caseDescription contains UPDATED_CASE_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("caseDescription.contains=" + UPDATED_CASE_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseDescription does not contain DEFAULT_CASE_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("caseDescription.doesNotContain=" + DEFAULT_CASE_DESCRIPTION);

        // Get all the courtCaseList where caseDescription does not contain UPDATED_CASE_DESCRIPTION
        defaultCourtCaseShouldBeFound("caseDescription.doesNotContain=" + UPDATED_CASE_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseFilingDateIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseFilingDate equals to DEFAULT_CASE_FILING_DATE
        defaultCourtCaseShouldBeFound("caseFilingDate.equals=" + DEFAULT_CASE_FILING_DATE);

        // Get all the courtCaseList where caseFilingDate equals to UPDATED_CASE_FILING_DATE
        defaultCourtCaseShouldNotBeFound("caseFilingDate.equals=" + UPDATED_CASE_FILING_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseFilingDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseFilingDate not equals to DEFAULT_CASE_FILING_DATE
        defaultCourtCaseShouldNotBeFound("caseFilingDate.notEquals=" + DEFAULT_CASE_FILING_DATE);

        // Get all the courtCaseList where caseFilingDate not equals to UPDATED_CASE_FILING_DATE
        defaultCourtCaseShouldBeFound("caseFilingDate.notEquals=" + UPDATED_CASE_FILING_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseFilingDateIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseFilingDate in DEFAULT_CASE_FILING_DATE or UPDATED_CASE_FILING_DATE
        defaultCourtCaseShouldBeFound("caseFilingDate.in=" + DEFAULT_CASE_FILING_DATE + "," + UPDATED_CASE_FILING_DATE);

        // Get all the courtCaseList where caseFilingDate equals to UPDATED_CASE_FILING_DATE
        defaultCourtCaseShouldNotBeFound("caseFilingDate.in=" + UPDATED_CASE_FILING_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseFilingDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseFilingDate is not null
        defaultCourtCaseShouldBeFound("caseFilingDate.specified=true");

        // Get all the courtCaseList where caseFilingDate is null
        defaultCourtCaseShouldNotBeFound("caseFilingDate.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByTotalClaimAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where totalClaimAmount equals to DEFAULT_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldBeFound("totalClaimAmount.equals=" + DEFAULT_TOTAL_CLAIM_AMOUNT);

        // Get all the courtCaseList where totalClaimAmount equals to UPDATED_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldNotBeFound("totalClaimAmount.equals=" + UPDATED_TOTAL_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByTotalClaimAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where totalClaimAmount not equals to DEFAULT_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldNotBeFound("totalClaimAmount.notEquals=" + DEFAULT_TOTAL_CLAIM_AMOUNT);

        // Get all the courtCaseList where totalClaimAmount not equals to UPDATED_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldBeFound("totalClaimAmount.notEquals=" + UPDATED_TOTAL_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByTotalClaimAmountIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where totalClaimAmount in DEFAULT_TOTAL_CLAIM_AMOUNT or UPDATED_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldBeFound("totalClaimAmount.in=" + DEFAULT_TOTAL_CLAIM_AMOUNT + "," + UPDATED_TOTAL_CLAIM_AMOUNT);

        // Get all the courtCaseList where totalClaimAmount equals to UPDATED_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldNotBeFound("totalClaimAmount.in=" + UPDATED_TOTAL_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByTotalClaimAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where totalClaimAmount is not null
        defaultCourtCaseShouldBeFound("totalClaimAmount.specified=true");

        // Get all the courtCaseList where totalClaimAmount is null
        defaultCourtCaseShouldNotBeFound("totalClaimAmount.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByTotalClaimAmountContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where totalClaimAmount contains DEFAULT_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldBeFound("totalClaimAmount.contains=" + DEFAULT_TOTAL_CLAIM_AMOUNT);

        // Get all the courtCaseList where totalClaimAmount contains UPDATED_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldNotBeFound("totalClaimAmount.contains=" + UPDATED_TOTAL_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByTotalClaimAmountNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where totalClaimAmount does not contain DEFAULT_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldNotBeFound("totalClaimAmount.doesNotContain=" + DEFAULT_TOTAL_CLAIM_AMOUNT);

        // Get all the courtCaseList where totalClaimAmount does not contain UPDATED_TOTAL_CLAIM_AMOUNT
        defaultCourtCaseShouldBeFound("totalClaimAmount.doesNotContain=" + UPDATED_TOTAL_CLAIM_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseOfficerIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseOfficer equals to DEFAULT_CASE_OFFICER
        defaultCourtCaseShouldBeFound("caseOfficer.equals=" + DEFAULT_CASE_OFFICER);

        // Get all the courtCaseList where caseOfficer equals to UPDATED_CASE_OFFICER
        defaultCourtCaseShouldNotBeFound("caseOfficer.equals=" + UPDATED_CASE_OFFICER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseOfficerIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseOfficer not equals to DEFAULT_CASE_OFFICER
        defaultCourtCaseShouldNotBeFound("caseOfficer.notEquals=" + DEFAULT_CASE_OFFICER);

        // Get all the courtCaseList where caseOfficer not equals to UPDATED_CASE_OFFICER
        defaultCourtCaseShouldBeFound("caseOfficer.notEquals=" + UPDATED_CASE_OFFICER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseOfficerIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseOfficer in DEFAULT_CASE_OFFICER or UPDATED_CASE_OFFICER
        defaultCourtCaseShouldBeFound("caseOfficer.in=" + DEFAULT_CASE_OFFICER + "," + UPDATED_CASE_OFFICER);

        // Get all the courtCaseList where caseOfficer equals to UPDATED_CASE_OFFICER
        defaultCourtCaseShouldNotBeFound("caseOfficer.in=" + UPDATED_CASE_OFFICER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseOfficerIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseOfficer is not null
        defaultCourtCaseShouldBeFound("caseOfficer.specified=true");

        // Get all the courtCaseList where caseOfficer is null
        defaultCourtCaseShouldNotBeFound("caseOfficer.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseOfficerContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseOfficer contains DEFAULT_CASE_OFFICER
        defaultCourtCaseShouldBeFound("caseOfficer.contains=" + DEFAULT_CASE_OFFICER);

        // Get all the courtCaseList where caseOfficer contains UPDATED_CASE_OFFICER
        defaultCourtCaseShouldNotBeFound("caseOfficer.contains=" + UPDATED_CASE_OFFICER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaseOfficerNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caseOfficer does not contain DEFAULT_CASE_OFFICER
        defaultCourtCaseShouldNotBeFound("caseOfficer.doesNotContain=" + DEFAULT_CASE_OFFICER);

        // Get all the courtCaseList where caseOfficer does not contain UPDATED_CASE_OFFICER
        defaultCourtCaseShouldBeFound("caseOfficer.doesNotContain=" + UPDATED_CASE_OFFICER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaselawyerIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caselawyer equals to DEFAULT_CASELAWYER
        defaultCourtCaseShouldBeFound("caselawyer.equals=" + DEFAULT_CASELAWYER);

        // Get all the courtCaseList where caselawyer equals to UPDATED_CASELAWYER
        defaultCourtCaseShouldNotBeFound("caselawyer.equals=" + UPDATED_CASELAWYER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaselawyerIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caselawyer not equals to DEFAULT_CASELAWYER
        defaultCourtCaseShouldNotBeFound("caselawyer.notEquals=" + DEFAULT_CASELAWYER);

        // Get all the courtCaseList where caselawyer not equals to UPDATED_CASELAWYER
        defaultCourtCaseShouldBeFound("caselawyer.notEquals=" + UPDATED_CASELAWYER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaselawyerIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caselawyer in DEFAULT_CASELAWYER or UPDATED_CASELAWYER
        defaultCourtCaseShouldBeFound("caselawyer.in=" + DEFAULT_CASELAWYER + "," + UPDATED_CASELAWYER);

        // Get all the courtCaseList where caselawyer equals to UPDATED_CASELAWYER
        defaultCourtCaseShouldNotBeFound("caselawyer.in=" + UPDATED_CASELAWYER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaselawyerIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caselawyer is not null
        defaultCourtCaseShouldBeFound("caselawyer.specified=true");

        // Get all the courtCaseList where caselawyer is null
        defaultCourtCaseShouldNotBeFound("caselawyer.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaselawyerContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caselawyer contains DEFAULT_CASELAWYER
        defaultCourtCaseShouldBeFound("caselawyer.contains=" + DEFAULT_CASELAWYER);

        // Get all the courtCaseList where caselawyer contains UPDATED_CASELAWYER
        defaultCourtCaseShouldNotBeFound("caselawyer.contains=" + UPDATED_CASELAWYER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCaselawyerNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where caselawyer does not contain DEFAULT_CASELAWYER
        defaultCourtCaseShouldNotBeFound("caselawyer.doesNotContain=" + DEFAULT_CASELAWYER);

        // Get all the courtCaseList where caselawyer does not contain UPDATED_CASELAWYER
        defaultCourtCaseShouldBeFound("caselawyer.doesNotContain=" + UPDATED_CASELAWYER);
    }

    @Test
    @Transactional
    void getAllCourtCasesByNextHearingDateIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where nextHearingDate equals to DEFAULT_NEXT_HEARING_DATE
        defaultCourtCaseShouldBeFound("nextHearingDate.equals=" + DEFAULT_NEXT_HEARING_DATE);

        // Get all the courtCaseList where nextHearingDate equals to UPDATED_NEXT_HEARING_DATE
        defaultCourtCaseShouldNotBeFound("nextHearingDate.equals=" + UPDATED_NEXT_HEARING_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByNextHearingDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where nextHearingDate not equals to DEFAULT_NEXT_HEARING_DATE
        defaultCourtCaseShouldNotBeFound("nextHearingDate.notEquals=" + DEFAULT_NEXT_HEARING_DATE);

        // Get all the courtCaseList where nextHearingDate not equals to UPDATED_NEXT_HEARING_DATE
        defaultCourtCaseShouldBeFound("nextHearingDate.notEquals=" + UPDATED_NEXT_HEARING_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByNextHearingDateIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where nextHearingDate in DEFAULT_NEXT_HEARING_DATE or UPDATED_NEXT_HEARING_DATE
        defaultCourtCaseShouldBeFound("nextHearingDate.in=" + DEFAULT_NEXT_HEARING_DATE + "," + UPDATED_NEXT_HEARING_DATE);

        // Get all the courtCaseList where nextHearingDate equals to UPDATED_NEXT_HEARING_DATE
        defaultCourtCaseShouldNotBeFound("nextHearingDate.in=" + UPDATED_NEXT_HEARING_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByNextHearingDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where nextHearingDate is not null
        defaultCourtCaseShouldBeFound("nextHearingDate.specified=true");

        // Get all the courtCaseList where nextHearingDate is null
        defaultCourtCaseShouldNotBeFound("nextHearingDate.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountDepositeInCourtIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountDepositeInCourt equals to DEFAULT_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldBeFound("amountDepositeInCourt.equals=" + DEFAULT_AMOUNT_DEPOSITE_IN_COURT);

        // Get all the courtCaseList where amountDepositeInCourt equals to UPDATED_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldNotBeFound("amountDepositeInCourt.equals=" + UPDATED_AMOUNT_DEPOSITE_IN_COURT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountDepositeInCourtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountDepositeInCourt not equals to DEFAULT_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldNotBeFound("amountDepositeInCourt.notEquals=" + DEFAULT_AMOUNT_DEPOSITE_IN_COURT);

        // Get all the courtCaseList where amountDepositeInCourt not equals to UPDATED_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldBeFound("amountDepositeInCourt.notEquals=" + UPDATED_AMOUNT_DEPOSITE_IN_COURT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountDepositeInCourtIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountDepositeInCourt in DEFAULT_AMOUNT_DEPOSITE_IN_COURT or UPDATED_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldBeFound(
            "amountDepositeInCourt.in=" + DEFAULT_AMOUNT_DEPOSITE_IN_COURT + "," + UPDATED_AMOUNT_DEPOSITE_IN_COURT
        );

        // Get all the courtCaseList where amountDepositeInCourt equals to UPDATED_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldNotBeFound("amountDepositeInCourt.in=" + UPDATED_AMOUNT_DEPOSITE_IN_COURT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountDepositeInCourtIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountDepositeInCourt is not null
        defaultCourtCaseShouldBeFound("amountDepositeInCourt.specified=true");

        // Get all the courtCaseList where amountDepositeInCourt is null
        defaultCourtCaseShouldNotBeFound("amountDepositeInCourt.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountDepositeInCourtContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountDepositeInCourt contains DEFAULT_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldBeFound("amountDepositeInCourt.contains=" + DEFAULT_AMOUNT_DEPOSITE_IN_COURT);

        // Get all the courtCaseList where amountDepositeInCourt contains UPDATED_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldNotBeFound("amountDepositeInCourt.contains=" + UPDATED_AMOUNT_DEPOSITE_IN_COURT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountDepositeInCourtNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountDepositeInCourt does not contain DEFAULT_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldNotBeFound("amountDepositeInCourt.doesNotContain=" + DEFAULT_AMOUNT_DEPOSITE_IN_COURT);

        // Get all the courtCaseList where amountDepositeInCourt does not contain UPDATED_AMOUNT_DEPOSITE_IN_COURT
        defaultCourtCaseShouldBeFound("amountDepositeInCourt.doesNotContain=" + UPDATED_AMOUNT_DEPOSITE_IN_COURT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLarIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lar equals to DEFAULT_LAR
        defaultCourtCaseShouldBeFound("lar.equals=" + DEFAULT_LAR);

        // Get all the courtCaseList where lar equals to UPDATED_LAR
        defaultCourtCaseShouldNotBeFound("lar.equals=" + UPDATED_LAR);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLarIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lar not equals to DEFAULT_LAR
        defaultCourtCaseShouldNotBeFound("lar.notEquals=" + DEFAULT_LAR);

        // Get all the courtCaseList where lar not equals to UPDATED_LAR
        defaultCourtCaseShouldBeFound("lar.notEquals=" + UPDATED_LAR);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLarIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lar in DEFAULT_LAR or UPDATED_LAR
        defaultCourtCaseShouldBeFound("lar.in=" + DEFAULT_LAR + "," + UPDATED_LAR);

        // Get all the courtCaseList where lar equals to UPDATED_LAR
        defaultCourtCaseShouldNotBeFound("lar.in=" + UPDATED_LAR);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLarIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lar is not null
        defaultCourtCaseShouldBeFound("lar.specified=true");

        // Get all the courtCaseList where lar is null
        defaultCourtCaseShouldNotBeFound("lar.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByLarContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lar contains DEFAULT_LAR
        defaultCourtCaseShouldBeFound("lar.contains=" + DEFAULT_LAR);

        // Get all the courtCaseList where lar contains UPDATED_LAR
        defaultCourtCaseShouldNotBeFound("lar.contains=" + UPDATED_LAR);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLarNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lar does not contain DEFAULT_LAR
        defaultCourtCaseShouldNotBeFound("lar.doesNotContain=" + DEFAULT_LAR);

        // Get all the courtCaseList where lar does not contain UPDATED_LAR
        defaultCourtCaseShouldBeFound("lar.doesNotContain=" + UPDATED_LAR);
    }

    @Test
    @Transactional
    void getAllCourtCasesByIncCompensationIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where incCompensation equals to DEFAULT_INC_COMPENSATION
        defaultCourtCaseShouldBeFound("incCompensation.equals=" + DEFAULT_INC_COMPENSATION);

        // Get all the courtCaseList where incCompensation equals to UPDATED_INC_COMPENSATION
        defaultCourtCaseShouldNotBeFound("incCompensation.equals=" + UPDATED_INC_COMPENSATION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByIncCompensationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where incCompensation not equals to DEFAULT_INC_COMPENSATION
        defaultCourtCaseShouldNotBeFound("incCompensation.notEquals=" + DEFAULT_INC_COMPENSATION);

        // Get all the courtCaseList where incCompensation not equals to UPDATED_INC_COMPENSATION
        defaultCourtCaseShouldBeFound("incCompensation.notEquals=" + UPDATED_INC_COMPENSATION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByIncCompensationIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where incCompensation in DEFAULT_INC_COMPENSATION or UPDATED_INC_COMPENSATION
        defaultCourtCaseShouldBeFound("incCompensation.in=" + DEFAULT_INC_COMPENSATION + "," + UPDATED_INC_COMPENSATION);

        // Get all the courtCaseList where incCompensation equals to UPDATED_INC_COMPENSATION
        defaultCourtCaseShouldNotBeFound("incCompensation.in=" + UPDATED_INC_COMPENSATION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByIncCompensationIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where incCompensation is not null
        defaultCourtCaseShouldBeFound("incCompensation.specified=true");

        // Get all the courtCaseList where incCompensation is null
        defaultCourtCaseShouldNotBeFound("incCompensation.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByIncCompensationContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where incCompensation contains DEFAULT_INC_COMPENSATION
        defaultCourtCaseShouldBeFound("incCompensation.contains=" + DEFAULT_INC_COMPENSATION);

        // Get all the courtCaseList where incCompensation contains UPDATED_INC_COMPENSATION
        defaultCourtCaseShouldNotBeFound("incCompensation.contains=" + UPDATED_INC_COMPENSATION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByIncCompensationNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where incCompensation does not contain DEFAULT_INC_COMPENSATION
        defaultCourtCaseShouldNotBeFound("incCompensation.doesNotContain=" + DEFAULT_INC_COMPENSATION);

        // Get all the courtCaseList where incCompensation does not contain UPDATED_INC_COMPENSATION
        defaultCourtCaseShouldBeFound("incCompensation.doesNotContain=" + UPDATED_INC_COMPENSATION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountPaidSLOIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountPaidSLO equals to DEFAULT_AMOUNT_PAID_SLO
        defaultCourtCaseShouldBeFound("amountPaidSLO.equals=" + DEFAULT_AMOUNT_PAID_SLO);

        // Get all the courtCaseList where amountPaidSLO equals to UPDATED_AMOUNT_PAID_SLO
        defaultCourtCaseShouldNotBeFound("amountPaidSLO.equals=" + UPDATED_AMOUNT_PAID_SLO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountPaidSLOIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountPaidSLO not equals to DEFAULT_AMOUNT_PAID_SLO
        defaultCourtCaseShouldNotBeFound("amountPaidSLO.notEquals=" + DEFAULT_AMOUNT_PAID_SLO);

        // Get all the courtCaseList where amountPaidSLO not equals to UPDATED_AMOUNT_PAID_SLO
        defaultCourtCaseShouldBeFound("amountPaidSLO.notEquals=" + UPDATED_AMOUNT_PAID_SLO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountPaidSLOIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountPaidSLO in DEFAULT_AMOUNT_PAID_SLO or UPDATED_AMOUNT_PAID_SLO
        defaultCourtCaseShouldBeFound("amountPaidSLO.in=" + DEFAULT_AMOUNT_PAID_SLO + "," + UPDATED_AMOUNT_PAID_SLO);

        // Get all the courtCaseList where amountPaidSLO equals to UPDATED_AMOUNT_PAID_SLO
        defaultCourtCaseShouldNotBeFound("amountPaidSLO.in=" + UPDATED_AMOUNT_PAID_SLO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountPaidSLOIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountPaidSLO is not null
        defaultCourtCaseShouldBeFound("amountPaidSLO.specified=true");

        // Get all the courtCaseList where amountPaidSLO is null
        defaultCourtCaseShouldNotBeFound("amountPaidSLO.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountPaidSLOContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountPaidSLO contains DEFAULT_AMOUNT_PAID_SLO
        defaultCourtCaseShouldBeFound("amountPaidSLO.contains=" + DEFAULT_AMOUNT_PAID_SLO);

        // Get all the courtCaseList where amountPaidSLO contains UPDATED_AMOUNT_PAID_SLO
        defaultCourtCaseShouldNotBeFound("amountPaidSLO.contains=" + UPDATED_AMOUNT_PAID_SLO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAmountPaidSLONotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where amountPaidSLO does not contain DEFAULT_AMOUNT_PAID_SLO
        defaultCourtCaseShouldNotBeFound("amountPaidSLO.doesNotContain=" + DEFAULT_AMOUNT_PAID_SLO);

        // Get all the courtCaseList where amountPaidSLO does not contain UPDATED_AMOUNT_PAID_SLO
        defaultCourtCaseShouldBeFound("amountPaidSLO.doesNotContain=" + UPDATED_AMOUNT_PAID_SLO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeNoIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeNo equals to DEFAULT_CHEQUE_NO
        defaultCourtCaseShouldBeFound("chequeNo.equals=" + DEFAULT_CHEQUE_NO);

        // Get all the courtCaseList where chequeNo equals to UPDATED_CHEQUE_NO
        defaultCourtCaseShouldNotBeFound("chequeNo.equals=" + UPDATED_CHEQUE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeNo not equals to DEFAULT_CHEQUE_NO
        defaultCourtCaseShouldNotBeFound("chequeNo.notEquals=" + DEFAULT_CHEQUE_NO);

        // Get all the courtCaseList where chequeNo not equals to UPDATED_CHEQUE_NO
        defaultCourtCaseShouldBeFound("chequeNo.notEquals=" + UPDATED_CHEQUE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeNoIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeNo in DEFAULT_CHEQUE_NO or UPDATED_CHEQUE_NO
        defaultCourtCaseShouldBeFound("chequeNo.in=" + DEFAULT_CHEQUE_NO + "," + UPDATED_CHEQUE_NO);

        // Get all the courtCaseList where chequeNo equals to UPDATED_CHEQUE_NO
        defaultCourtCaseShouldNotBeFound("chequeNo.in=" + UPDATED_CHEQUE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeNo is not null
        defaultCourtCaseShouldBeFound("chequeNo.specified=true");

        // Get all the courtCaseList where chequeNo is null
        defaultCourtCaseShouldNotBeFound("chequeNo.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeNoContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeNo contains DEFAULT_CHEQUE_NO
        defaultCourtCaseShouldBeFound("chequeNo.contains=" + DEFAULT_CHEQUE_NO);

        // Get all the courtCaseList where chequeNo contains UPDATED_CHEQUE_NO
        defaultCourtCaseShouldNotBeFound("chequeNo.contains=" + UPDATED_CHEQUE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeNoNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeNo does not contain DEFAULT_CHEQUE_NO
        defaultCourtCaseShouldNotBeFound("chequeNo.doesNotContain=" + DEFAULT_CHEQUE_NO);

        // Get all the courtCaseList where chequeNo does not contain UPDATED_CHEQUE_NO
        defaultCourtCaseShouldBeFound("chequeNo.doesNotContain=" + UPDATED_CHEQUE_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeDateIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeDate equals to DEFAULT_CHEQUE_DATE
        defaultCourtCaseShouldBeFound("chequeDate.equals=" + DEFAULT_CHEQUE_DATE);

        // Get all the courtCaseList where chequeDate equals to UPDATED_CHEQUE_DATE
        defaultCourtCaseShouldNotBeFound("chequeDate.equals=" + UPDATED_CHEQUE_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeDate not equals to DEFAULT_CHEQUE_DATE
        defaultCourtCaseShouldNotBeFound("chequeDate.notEquals=" + DEFAULT_CHEQUE_DATE);

        // Get all the courtCaseList where chequeDate not equals to UPDATED_CHEQUE_DATE
        defaultCourtCaseShouldBeFound("chequeDate.notEquals=" + UPDATED_CHEQUE_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeDateIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeDate in DEFAULT_CHEQUE_DATE or UPDATED_CHEQUE_DATE
        defaultCourtCaseShouldBeFound("chequeDate.in=" + DEFAULT_CHEQUE_DATE + "," + UPDATED_CHEQUE_DATE);

        // Get all the courtCaseList where chequeDate equals to UPDATED_CHEQUE_DATE
        defaultCourtCaseShouldNotBeFound("chequeDate.in=" + UPDATED_CHEQUE_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByChequeDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where chequeDate is not null
        defaultCourtCaseShouldBeFound("chequeDate.specified=true");

        // Get all the courtCaseList where chequeDate is null
        defaultCourtCaseShouldNotBeFound("chequeDate.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealNoIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealNo equals to DEFAULT_APPEAL_NO
        defaultCourtCaseShouldBeFound("appealNo.equals=" + DEFAULT_APPEAL_NO);

        // Get all the courtCaseList where appealNo equals to UPDATED_APPEAL_NO
        defaultCourtCaseShouldNotBeFound("appealNo.equals=" + UPDATED_APPEAL_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealNo not equals to DEFAULT_APPEAL_NO
        defaultCourtCaseShouldNotBeFound("appealNo.notEquals=" + DEFAULT_APPEAL_NO);

        // Get all the courtCaseList where appealNo not equals to UPDATED_APPEAL_NO
        defaultCourtCaseShouldBeFound("appealNo.notEquals=" + UPDATED_APPEAL_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealNoIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealNo in DEFAULT_APPEAL_NO or UPDATED_APPEAL_NO
        defaultCourtCaseShouldBeFound("appealNo.in=" + DEFAULT_APPEAL_NO + "," + UPDATED_APPEAL_NO);

        // Get all the courtCaseList where appealNo equals to UPDATED_APPEAL_NO
        defaultCourtCaseShouldNotBeFound("appealNo.in=" + UPDATED_APPEAL_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealNo is not null
        defaultCourtCaseShouldBeFound("appealNo.specified=true");

        // Get all the courtCaseList where appealNo is null
        defaultCourtCaseShouldNotBeFound("appealNo.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealNoContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealNo contains DEFAULT_APPEAL_NO
        defaultCourtCaseShouldBeFound("appealNo.contains=" + DEFAULT_APPEAL_NO);

        // Get all the courtCaseList where appealNo contains UPDATED_APPEAL_NO
        defaultCourtCaseShouldNotBeFound("appealNo.contains=" + UPDATED_APPEAL_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealNoNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealNo does not contain DEFAULT_APPEAL_NO
        defaultCourtCaseShouldNotBeFound("appealNo.doesNotContain=" + DEFAULT_APPEAL_NO);

        // Get all the courtCaseList where appealNo does not contain UPDATED_APPEAL_NO
        defaultCourtCaseShouldBeFound("appealNo.doesNotContain=" + UPDATED_APPEAL_NO);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtAmount equals to DEFAULT_COURT_AMOUNT
        defaultCourtCaseShouldBeFound("courtAmount.equals=" + DEFAULT_COURT_AMOUNT);

        // Get all the courtCaseList where courtAmount equals to UPDATED_COURT_AMOUNT
        defaultCourtCaseShouldNotBeFound("courtAmount.equals=" + UPDATED_COURT_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtAmount not equals to DEFAULT_COURT_AMOUNT
        defaultCourtCaseShouldNotBeFound("courtAmount.notEquals=" + DEFAULT_COURT_AMOUNT);

        // Get all the courtCaseList where courtAmount not equals to UPDATED_COURT_AMOUNT
        defaultCourtCaseShouldBeFound("courtAmount.notEquals=" + UPDATED_COURT_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtAmountIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtAmount in DEFAULT_COURT_AMOUNT or UPDATED_COURT_AMOUNT
        defaultCourtCaseShouldBeFound("courtAmount.in=" + DEFAULT_COURT_AMOUNT + "," + UPDATED_COURT_AMOUNT);

        // Get all the courtCaseList where courtAmount equals to UPDATED_COURT_AMOUNT
        defaultCourtCaseShouldNotBeFound("courtAmount.in=" + UPDATED_COURT_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtAmount is not null
        defaultCourtCaseShouldBeFound("courtAmount.specified=true");

        // Get all the courtCaseList where courtAmount is null
        defaultCourtCaseShouldNotBeFound("courtAmount.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtAmountContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtAmount contains DEFAULT_COURT_AMOUNT
        defaultCourtCaseShouldBeFound("courtAmount.contains=" + DEFAULT_COURT_AMOUNT);

        // Get all the courtCaseList where courtAmount contains UPDATED_COURT_AMOUNT
        defaultCourtCaseShouldNotBeFound("courtAmount.contains=" + UPDATED_COURT_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCourtAmountNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where courtAmount does not contain DEFAULT_COURT_AMOUNT
        defaultCourtCaseShouldNotBeFound("courtAmount.doesNotContain=" + DEFAULT_COURT_AMOUNT);

        // Get all the courtCaseList where courtAmount does not contain UPDATED_COURT_AMOUNT
        defaultCourtCaseShouldBeFound("courtAmount.doesNotContain=" + UPDATED_COURT_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealAmount equals to DEFAULT_APPEAL_AMOUNT
        defaultCourtCaseShouldBeFound("appealAmount.equals=" + DEFAULT_APPEAL_AMOUNT);

        // Get all the courtCaseList where appealAmount equals to UPDATED_APPEAL_AMOUNT
        defaultCourtCaseShouldNotBeFound("appealAmount.equals=" + UPDATED_APPEAL_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealAmountIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealAmount not equals to DEFAULT_APPEAL_AMOUNT
        defaultCourtCaseShouldNotBeFound("appealAmount.notEquals=" + DEFAULT_APPEAL_AMOUNT);

        // Get all the courtCaseList where appealAmount not equals to UPDATED_APPEAL_AMOUNT
        defaultCourtCaseShouldBeFound("appealAmount.notEquals=" + UPDATED_APPEAL_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealAmountIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealAmount in DEFAULT_APPEAL_AMOUNT or UPDATED_APPEAL_AMOUNT
        defaultCourtCaseShouldBeFound("appealAmount.in=" + DEFAULT_APPEAL_AMOUNT + "," + UPDATED_APPEAL_AMOUNT);

        // Get all the courtCaseList where appealAmount equals to UPDATED_APPEAL_AMOUNT
        defaultCourtCaseShouldNotBeFound("appealAmount.in=" + UPDATED_APPEAL_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealAmount is not null
        defaultCourtCaseShouldBeFound("appealAmount.specified=true");

        // Get all the courtCaseList where appealAmount is null
        defaultCourtCaseShouldNotBeFound("appealAmount.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealAmountContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealAmount contains DEFAULT_APPEAL_AMOUNT
        defaultCourtCaseShouldBeFound("appealAmount.contains=" + DEFAULT_APPEAL_AMOUNT);

        // Get all the courtCaseList where appealAmount contains UPDATED_APPEAL_AMOUNT
        defaultCourtCaseShouldNotBeFound("appealAmount.contains=" + UPDATED_APPEAL_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealAmountNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealAmount does not contain DEFAULT_APPEAL_AMOUNT
        defaultCourtCaseShouldNotBeFound("appealAmount.doesNotContain=" + DEFAULT_APPEAL_AMOUNT);

        // Get all the courtCaseList where appealAmount does not contain UPDATED_APPEAL_AMOUNT
        defaultCourtCaseShouldBeFound("appealAmount.doesNotContain=" + UPDATED_APPEAL_AMOUNT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealDateIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealDate equals to DEFAULT_APPEAL_DATE
        defaultCourtCaseShouldBeFound("appealDate.equals=" + DEFAULT_APPEAL_DATE);

        // Get all the courtCaseList where appealDate equals to UPDATED_APPEAL_DATE
        defaultCourtCaseShouldNotBeFound("appealDate.equals=" + UPDATED_APPEAL_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealDate not equals to DEFAULT_APPEAL_DATE
        defaultCourtCaseShouldNotBeFound("appealDate.notEquals=" + DEFAULT_APPEAL_DATE);

        // Get all the courtCaseList where appealDate not equals to UPDATED_APPEAL_DATE
        defaultCourtCaseShouldBeFound("appealDate.notEquals=" + UPDATED_APPEAL_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealDateIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealDate in DEFAULT_APPEAL_DATE or UPDATED_APPEAL_DATE
        defaultCourtCaseShouldBeFound("appealDate.in=" + DEFAULT_APPEAL_DATE + "," + UPDATED_APPEAL_DATE);

        // Get all the courtCaseList where appealDate equals to UPDATED_APPEAL_DATE
        defaultCourtCaseShouldNotBeFound("appealDate.in=" + UPDATED_APPEAL_DATE);
    }

    @Test
    @Transactional
    void getAllCourtCasesByAppealDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where appealDate is not null
        defaultCourtCaseShouldBeFound("appealDate.specified=true");

        // Get all the courtCaseList where appealDate is null
        defaultCourtCaseShouldNotBeFound("appealDate.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where description equals to DEFAULT_DESCRIPTION
        defaultCourtCaseShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the courtCaseList where description equals to UPDATED_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where description not equals to DEFAULT_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the courtCaseList where description not equals to UPDATED_DESCRIPTION
        defaultCourtCaseShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultCourtCaseShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the courtCaseList where description equals to UPDATED_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where description is not null
        defaultCourtCaseShouldBeFound("description.specified=true");

        // Get all the courtCaseList where description is null
        defaultCourtCaseShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where description contains DEFAULT_DESCRIPTION
        defaultCourtCaseShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the courtCaseList where description contains UPDATED_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where description does not contain DEFAULT_DESCRIPTION
        defaultCourtCaseShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the courtCaseList where description does not contain UPDATED_DESCRIPTION
        defaultCourtCaseShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where comment equals to DEFAULT_COMMENT
        defaultCourtCaseShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the courtCaseList where comment equals to UPDATED_COMMENT
        defaultCourtCaseShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCommentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where comment not equals to DEFAULT_COMMENT
        defaultCourtCaseShouldNotBeFound("comment.notEquals=" + DEFAULT_COMMENT);

        // Get all the courtCaseList where comment not equals to UPDATED_COMMENT
        defaultCourtCaseShouldBeFound("comment.notEquals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultCourtCaseShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the courtCaseList where comment equals to UPDATED_COMMENT
        defaultCourtCaseShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where comment is not null
        defaultCourtCaseShouldBeFound("comment.specified=true");

        // Get all the courtCaseList where comment is null
        defaultCourtCaseShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByCommentContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where comment contains DEFAULT_COMMENT
        defaultCourtCaseShouldBeFound("comment.contains=" + DEFAULT_COMMENT);

        // Get all the courtCaseList where comment contains UPDATED_COMMENT
        defaultCourtCaseShouldNotBeFound("comment.contains=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByCommentNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where comment does not contain DEFAULT_COMMENT
        defaultCourtCaseShouldNotBeFound("comment.doesNotContain=" + DEFAULT_COMMENT);

        // Get all the courtCaseList where comment does not contain UPDATED_COMMENT
        defaultCourtCaseShouldBeFound("comment.doesNotContain=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void getAllCourtCasesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where status equals to DEFAULT_STATUS
        defaultCourtCaseShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the courtCaseList where status equals to UPDATED_STATUS
        defaultCourtCaseShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllCourtCasesByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where status not equals to DEFAULT_STATUS
        defaultCourtCaseShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the courtCaseList where status not equals to UPDATED_STATUS
        defaultCourtCaseShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllCourtCasesByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultCourtCaseShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the courtCaseList where status equals to UPDATED_STATUS
        defaultCourtCaseShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllCourtCasesByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where status is not null
        defaultCourtCaseShouldBeFound("status.specified=true");

        // Get all the courtCaseList where status is null
        defaultCourtCaseShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield1IsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield1 equals to DEFAULT_FREEFIELD_1
        defaultCourtCaseShouldBeFound("freefield1.equals=" + DEFAULT_FREEFIELD_1);

        // Get all the courtCaseList where freefield1 equals to UPDATED_FREEFIELD_1
        defaultCourtCaseShouldNotBeFound("freefield1.equals=" + UPDATED_FREEFIELD_1);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield1 not equals to DEFAULT_FREEFIELD_1
        defaultCourtCaseShouldNotBeFound("freefield1.notEquals=" + DEFAULT_FREEFIELD_1);

        // Get all the courtCaseList where freefield1 not equals to UPDATED_FREEFIELD_1
        defaultCourtCaseShouldBeFound("freefield1.notEquals=" + UPDATED_FREEFIELD_1);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield1IsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield1 in DEFAULT_FREEFIELD_1 or UPDATED_FREEFIELD_1
        defaultCourtCaseShouldBeFound("freefield1.in=" + DEFAULT_FREEFIELD_1 + "," + UPDATED_FREEFIELD_1);

        // Get all the courtCaseList where freefield1 equals to UPDATED_FREEFIELD_1
        defaultCourtCaseShouldNotBeFound("freefield1.in=" + UPDATED_FREEFIELD_1);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield1IsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield1 is not null
        defaultCourtCaseShouldBeFound("freefield1.specified=true");

        // Get all the courtCaseList where freefield1 is null
        defaultCourtCaseShouldNotBeFound("freefield1.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield1ContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield1 contains DEFAULT_FREEFIELD_1
        defaultCourtCaseShouldBeFound("freefield1.contains=" + DEFAULT_FREEFIELD_1);

        // Get all the courtCaseList where freefield1 contains UPDATED_FREEFIELD_1
        defaultCourtCaseShouldNotBeFound("freefield1.contains=" + UPDATED_FREEFIELD_1);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield1NotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield1 does not contain DEFAULT_FREEFIELD_1
        defaultCourtCaseShouldNotBeFound("freefield1.doesNotContain=" + DEFAULT_FREEFIELD_1);

        // Get all the courtCaseList where freefield1 does not contain UPDATED_FREEFIELD_1
        defaultCourtCaseShouldBeFound("freefield1.doesNotContain=" + UPDATED_FREEFIELD_1);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield2IsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield2 equals to DEFAULT_FREEFIELD_2
        defaultCourtCaseShouldBeFound("freefield2.equals=" + DEFAULT_FREEFIELD_2);

        // Get all the courtCaseList where freefield2 equals to UPDATED_FREEFIELD_2
        defaultCourtCaseShouldNotBeFound("freefield2.equals=" + UPDATED_FREEFIELD_2);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield2 not equals to DEFAULT_FREEFIELD_2
        defaultCourtCaseShouldNotBeFound("freefield2.notEquals=" + DEFAULT_FREEFIELD_2);

        // Get all the courtCaseList where freefield2 not equals to UPDATED_FREEFIELD_2
        defaultCourtCaseShouldBeFound("freefield2.notEquals=" + UPDATED_FREEFIELD_2);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield2IsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield2 in DEFAULT_FREEFIELD_2 or UPDATED_FREEFIELD_2
        defaultCourtCaseShouldBeFound("freefield2.in=" + DEFAULT_FREEFIELD_2 + "," + UPDATED_FREEFIELD_2);

        // Get all the courtCaseList where freefield2 equals to UPDATED_FREEFIELD_2
        defaultCourtCaseShouldNotBeFound("freefield2.in=" + UPDATED_FREEFIELD_2);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield2IsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield2 is not null
        defaultCourtCaseShouldBeFound("freefield2.specified=true");

        // Get all the courtCaseList where freefield2 is null
        defaultCourtCaseShouldNotBeFound("freefield2.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield2ContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield2 contains DEFAULT_FREEFIELD_2
        defaultCourtCaseShouldBeFound("freefield2.contains=" + DEFAULT_FREEFIELD_2);

        // Get all the courtCaseList where freefield2 contains UPDATED_FREEFIELD_2
        defaultCourtCaseShouldNotBeFound("freefield2.contains=" + UPDATED_FREEFIELD_2);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield2NotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield2 does not contain DEFAULT_FREEFIELD_2
        defaultCourtCaseShouldNotBeFound("freefield2.doesNotContain=" + DEFAULT_FREEFIELD_2);

        // Get all the courtCaseList where freefield2 does not contain UPDATED_FREEFIELD_2
        defaultCourtCaseShouldBeFound("freefield2.doesNotContain=" + UPDATED_FREEFIELD_2);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield3IsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield3 equals to DEFAULT_FREEFIELD_3
        defaultCourtCaseShouldBeFound("freefield3.equals=" + DEFAULT_FREEFIELD_3);

        // Get all the courtCaseList where freefield3 equals to UPDATED_FREEFIELD_3
        defaultCourtCaseShouldNotBeFound("freefield3.equals=" + UPDATED_FREEFIELD_3);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield3 not equals to DEFAULT_FREEFIELD_3
        defaultCourtCaseShouldNotBeFound("freefield3.notEquals=" + DEFAULT_FREEFIELD_3);

        // Get all the courtCaseList where freefield3 not equals to UPDATED_FREEFIELD_3
        defaultCourtCaseShouldBeFound("freefield3.notEquals=" + UPDATED_FREEFIELD_3);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield3IsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield3 in DEFAULT_FREEFIELD_3 or UPDATED_FREEFIELD_3
        defaultCourtCaseShouldBeFound("freefield3.in=" + DEFAULT_FREEFIELD_3 + "," + UPDATED_FREEFIELD_3);

        // Get all the courtCaseList where freefield3 equals to UPDATED_FREEFIELD_3
        defaultCourtCaseShouldNotBeFound("freefield3.in=" + UPDATED_FREEFIELD_3);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield3IsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield3 is not null
        defaultCourtCaseShouldBeFound("freefield3.specified=true");

        // Get all the courtCaseList where freefield3 is null
        defaultCourtCaseShouldNotBeFound("freefield3.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield3ContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield3 contains DEFAULT_FREEFIELD_3
        defaultCourtCaseShouldBeFound("freefield3.contains=" + DEFAULT_FREEFIELD_3);

        // Get all the courtCaseList where freefield3 contains UPDATED_FREEFIELD_3
        defaultCourtCaseShouldNotBeFound("freefield3.contains=" + UPDATED_FREEFIELD_3);
    }

    @Test
    @Transactional
    void getAllCourtCasesByFreefield3NotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where freefield3 does not contain DEFAULT_FREEFIELD_3
        defaultCourtCaseShouldNotBeFound("freefield3.doesNotContain=" + DEFAULT_FREEFIELD_3);

        // Get all the courtCaseList where freefield3 does not contain UPDATED_FREEFIELD_3
        defaultCourtCaseShouldBeFound("freefield3.doesNotContain=" + UPDATED_FREEFIELD_3);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultCourtCaseShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the courtCaseList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultCourtCaseShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
        defaultCourtCaseShouldNotBeFound("lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the courtCaseList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
        defaultCourtCaseShouldBeFound("lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultCourtCaseShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the courtCaseList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultCourtCaseShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModifiedBy is not null
        defaultCourtCaseShouldBeFound("lastModifiedBy.specified=true");

        // Get all the courtCaseList where lastModifiedBy is null
        defaultCourtCaseShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultCourtCaseShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the courtCaseList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultCourtCaseShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultCourtCaseShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the courtCaseList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultCourtCaseShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultCourtCaseShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the courtCaseList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultCourtCaseShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModified not equals to DEFAULT_LAST_MODIFIED
        defaultCourtCaseShouldNotBeFound("lastModified.notEquals=" + DEFAULT_LAST_MODIFIED);

        // Get all the courtCaseList where lastModified not equals to UPDATED_LAST_MODIFIED
        defaultCourtCaseShouldBeFound("lastModified.notEquals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultCourtCaseShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the courtCaseList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultCourtCaseShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModified is not null
        defaultCourtCaseShouldBeFound("lastModified.specified=true");

        // Get all the courtCaseList where lastModified is null
        defaultCourtCaseShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModified contains DEFAULT_LAST_MODIFIED
        defaultCourtCaseShouldBeFound("lastModified.contains=" + DEFAULT_LAST_MODIFIED);

        // Get all the courtCaseList where lastModified contains UPDATED_LAST_MODIFIED
        defaultCourtCaseShouldNotBeFound("lastModified.contains=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllCourtCasesByLastModifiedNotContainsSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList where lastModified does not contain DEFAULT_LAST_MODIFIED
        defaultCourtCaseShouldNotBeFound("lastModified.doesNotContain=" + DEFAULT_LAST_MODIFIED);

        // Get all the courtCaseList where lastModified does not contain UPDATED_LAST_MODIFIED
        defaultCourtCaseShouldBeFound("lastModified.doesNotContain=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllCourtCasesByHearingIsEqualToSomething() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);
        Hearing hearing;
        if (TestUtil.findAll(em, Hearing.class).isEmpty()) {
            hearing = HearingResourceIT.createEntity(em);
            em.persist(hearing);
            em.flush();
        } else {
            hearing = TestUtil.findAll(em, Hearing.class).get(0);
        }
        em.persist(hearing);
        em.flush();
        courtCase.addHearing(hearing);
        courtCaseRepository.saveAndFlush(courtCase);
        Long hearingId = hearing.getId();

        // Get all the courtCaseList where hearing equals to hearingId
        defaultCourtCaseShouldBeFound("hearingId.equals=" + hearingId);

        // Get all the courtCaseList where hearing equals to (hearingId + 1)
        defaultCourtCaseShouldNotBeFound("hearingId.equals=" + (hearingId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCourtCaseShouldBeFound(String filter) throws Exception {
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courtCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseNo").value(hasItem(DEFAULT_CASE_NO)))
            .andExpect(jsonPath("$.[*].villageName").value(hasItem(DEFAULT_VILLAGE_NAME)))
            .andExpect(jsonPath("$.[*].accuserName").value(hasItem(DEFAULT_ACCUSER_NAME)))
            .andExpect(jsonPath("$.[*].applicationNo").value(hasItem(DEFAULT_APPLICATION_NO)))
            .andExpect(jsonPath("$.[*].landReferenceNo").value(hasItem(DEFAULT_LAND_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].firstAppeal").value(hasItem(DEFAULT_FIRST_APPEAL)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME)))
            .andExpect(jsonPath("$.[*].courtName").value(hasItem(DEFAULT_COURT_NAME)))
            .andExpect(jsonPath("$.[*].defendantName").value(hasItem(DEFAULT_DEFENDANT_NAME)))
            .andExpect(jsonPath("$.[*].caseDescription").value(hasItem(DEFAULT_CASE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].caseFilingDate").value(hasItem(DEFAULT_CASE_FILING_DATE.toString())))
            .andExpect(jsonPath("$.[*].totalClaimAmount").value(hasItem(DEFAULT_TOTAL_CLAIM_AMOUNT)))
            .andExpect(jsonPath("$.[*].caseOfficer").value(hasItem(DEFAULT_CASE_OFFICER)))
            .andExpect(jsonPath("$.[*].caselawyer").value(hasItem(DEFAULT_CASELAWYER)))
            .andExpect(jsonPath("$.[*].nextHearingDate").value(hasItem(DEFAULT_NEXT_HEARING_DATE.toString())))
            .andExpect(jsonPath("$.[*].amountDepositeInCourt").value(hasItem(DEFAULT_AMOUNT_DEPOSITE_IN_COURT)))
            .andExpect(jsonPath("$.[*].lar").value(hasItem(DEFAULT_LAR)))
            .andExpect(jsonPath("$.[*].incCompensation").value(hasItem(DEFAULT_INC_COMPENSATION)))
            .andExpect(jsonPath("$.[*].amountPaidSLO").value(hasItem(DEFAULT_AMOUNT_PAID_SLO)))
            .andExpect(jsonPath("$.[*].chequeNo").value(hasItem(DEFAULT_CHEQUE_NO)))
            .andExpect(jsonPath("$.[*].chequeDate").value(hasItem(DEFAULT_CHEQUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].appealNo").value(hasItem(DEFAULT_APPEAL_NO)))
            .andExpect(jsonPath("$.[*].courtAmount").value(hasItem(DEFAULT_COURT_AMOUNT)))
            .andExpect(jsonPath("$.[*].appealAmount").value(hasItem(DEFAULT_APPEAL_AMOUNT)))
            .andExpect(jsonPath("$.[*].appealDate").value(hasItem(DEFAULT_APPEAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].freefield1").value(hasItem(DEFAULT_FREEFIELD_1)))
            .andExpect(jsonPath("$.[*].freefield2").value(hasItem(DEFAULT_FREEFIELD_2)))
            .andExpect(jsonPath("$.[*].freefield3").value(hasItem(DEFAULT_FREEFIELD_3)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)));

        // Check, that the count call also returns 1
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCourtCaseShouldNotBeFound(String filter) throws Exception {
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCourtCase() throws Exception {
        // Get the courtCase
        restCourtCaseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCourtCase() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();

        // Update the courtCase
        CourtCase updatedCourtCase = courtCaseRepository.findById(courtCase.getId()).get();
        // Disconnect from session so that the updates on updatedCourtCase are not directly saved in db
        em.detach(updatedCourtCase);
        updatedCourtCase
            .caseNo(UPDATED_CASE_NO)
            .villageName(UPDATED_VILLAGE_NAME)
            .accuserName(UPDATED_ACCUSER_NAME)
            .applicationNo(UPDATED_APPLICATION_NO)
            .landReferenceNo(UPDATED_LAND_REFERENCE_NO)
            .firstAppeal(UPDATED_FIRST_APPEAL)
            .amount(UPDATED_AMOUNT)
            .projectName(UPDATED_PROJECT_NAME)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .totalClaimAmount(UPDATED_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(UPDATED_CASE_OFFICER)
            .caselawyer(UPDATED_CASELAWYER)
            .nextHearingDate(UPDATED_NEXT_HEARING_DATE)
            .amountDepositeInCourt(UPDATED_AMOUNT_DEPOSITE_IN_COURT)
            .lar(UPDATED_LAR)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .chequeDate(UPDATED_CHEQUE_DATE)
            .appealNo(UPDATED_APPEAL_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .appealDate(UPDATED_APPEAL_DATE)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield2(UPDATED_FREEFIELD_2)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModified(UPDATED_LAST_MODIFIED);
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(updatedCourtCase);

        restCourtCaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, courtCaseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isOk());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(UPDATED_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(UPDATED_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(UPDATED_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(UPDATED_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(UPDATED_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(UPDATED_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(UPDATED_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(UPDATED_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(UPDATED_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(UPDATED_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(UPDATED_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(UPDATED_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(UPDATED_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(UPDATED_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(UPDATED_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(UPDATED_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(UPDATED_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(UPDATED_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(UPDATED_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(UPDATED_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(UPDATED_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(UPDATED_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCourtCase.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(UPDATED_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(UPDATED_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(UPDATED_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void putNonExistingCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, courtCaseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courtCaseDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCourtCaseWithPatch() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();

        // Update the courtCase using partial update
        CourtCase partialUpdatedCourtCase = new CourtCase();
        partialUpdatedCourtCase.setId(courtCase.getId());

        partialUpdatedCourtCase
            .applicationNo(UPDATED_APPLICATION_NO)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .caselawyer(UPDATED_CASELAWYER)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModified(UPDATED_LAST_MODIFIED);

        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourtCase.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourtCase))
            )
            .andExpect(status().isOk());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(DEFAULT_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(DEFAULT_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(DEFAULT_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(UPDATED_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(DEFAULT_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(DEFAULT_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(UPDATED_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(UPDATED_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(UPDATED_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(DEFAULT_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(UPDATED_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(DEFAULT_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(DEFAULT_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(DEFAULT_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(UPDATED_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(UPDATED_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(UPDATED_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(DEFAULT_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(DEFAULT_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(UPDATED_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(UPDATED_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(DEFAULT_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCourtCase.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(UPDATED_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(DEFAULT_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(UPDATED_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void fullUpdateCourtCaseWithPatch() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();

        // Update the courtCase using partial update
        CourtCase partialUpdatedCourtCase = new CourtCase();
        partialUpdatedCourtCase.setId(courtCase.getId());

        partialUpdatedCourtCase
            .caseNo(UPDATED_CASE_NO)
            .villageName(UPDATED_VILLAGE_NAME)
            .accuserName(UPDATED_ACCUSER_NAME)
            .applicationNo(UPDATED_APPLICATION_NO)
            .landReferenceNo(UPDATED_LAND_REFERENCE_NO)
            .firstAppeal(UPDATED_FIRST_APPEAL)
            .amount(UPDATED_AMOUNT)
            .projectName(UPDATED_PROJECT_NAME)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .totalClaimAmount(UPDATED_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(UPDATED_CASE_OFFICER)
            .caselawyer(UPDATED_CASELAWYER)
            .nextHearingDate(UPDATED_NEXT_HEARING_DATE)
            .amountDepositeInCourt(UPDATED_AMOUNT_DEPOSITE_IN_COURT)
            .lar(UPDATED_LAR)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .chequeDate(UPDATED_CHEQUE_DATE)
            .appealNo(UPDATED_APPEAL_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .appealDate(UPDATED_APPEAL_DATE)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield2(UPDATED_FREEFIELD_2)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModified(UPDATED_LAST_MODIFIED);

        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourtCase.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourtCase))
            )
            .andExpect(status().isOk());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(UPDATED_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(UPDATED_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(UPDATED_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(UPDATED_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(UPDATED_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(UPDATED_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(UPDATED_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(UPDATED_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(UPDATED_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(UPDATED_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(UPDATED_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(UPDATED_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(UPDATED_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(UPDATED_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(UPDATED_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(UPDATED_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(UPDATED_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(UPDATED_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(UPDATED_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(UPDATED_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(UPDATED_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(UPDATED_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCourtCase.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(UPDATED_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(UPDATED_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(UPDATED_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void patchNonExistingCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, courtCaseDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCourtCase() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeDelete = courtCaseRepository.findAll().size();

        // Delete the courtCase
        restCourtCaseMockMvc
            .perform(delete(ENTITY_API_URL_ID, courtCase.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
