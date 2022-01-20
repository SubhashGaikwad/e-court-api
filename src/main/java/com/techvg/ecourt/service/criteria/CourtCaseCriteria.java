package com.techvg.ecourt.service.criteria;

import com.techvg.ecourt.domain.enumeration.Status;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.techvg.ecourt.domain.CourtCase} entity. This class is used
 * in {@link com.techvg.ecourt.web.rest.CourtCaseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /court-cases?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CourtCaseCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Status
     */
    public static class StatusFilter extends Filter<Status> {

        public StatusFilter() {}

        public StatusFilter(StatusFilter filter) {
            super(filter);
        }

        @Override
        public StatusFilter copy() {
            return new StatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter caseNo;

    private StringFilter villageName;

    private StringFilter accuserName;

    private StringFilter applicationNo;

    private StringFilter landReferenceNo;

    private StringFilter firstAppeal;

    private StringFilter amount;

    private StringFilter projectName;

    private StringFilter courtName;

    private StringFilter defendantName;

    private StringFilter caseDescription;

    private InstantFilter caseFilingDate;

    private StringFilter totalClaimAmount;

    private StringFilter caseOfficer;

    private StringFilter caselawyer;

    private InstantFilter nextHearingDate;

    private StringFilter amountDepositeInCourt;

    private StringFilter lar;

    private StringFilter incCompensation;

    private StringFilter amountPaidSLO;

    private StringFilter chequeNo;

    private InstantFilter chequeDate;

    private StringFilter appealNo;

    private StringFilter courtAmount;

    private StringFilter appealAmount;

    private InstantFilter appealDate;

    private StringFilter description;

    private StringFilter comment;

    private StatusFilter status;

    private StringFilter freefield1;

    private StringFilter freefield2;

    private StringFilter freefield3;

    private StringFilter lastModifiedBy;

    private StringFilter lastModified;

    private LongFilter hearingId;

    private Boolean distinct;

    public CourtCaseCriteria() {}

    public CourtCaseCriteria(CourtCaseCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.caseNo = other.caseNo == null ? null : other.caseNo.copy();
        this.villageName = other.villageName == null ? null : other.villageName.copy();
        this.accuserName = other.accuserName == null ? null : other.accuserName.copy();
        this.applicationNo = other.applicationNo == null ? null : other.applicationNo.copy();
        this.landReferenceNo = other.landReferenceNo == null ? null : other.landReferenceNo.copy();
        this.firstAppeal = other.firstAppeal == null ? null : other.firstAppeal.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.projectName = other.projectName == null ? null : other.projectName.copy();
        this.courtName = other.courtName == null ? null : other.courtName.copy();
        this.defendantName = other.defendantName == null ? null : other.defendantName.copy();
        this.caseDescription = other.caseDescription == null ? null : other.caseDescription.copy();
        this.caseFilingDate = other.caseFilingDate == null ? null : other.caseFilingDate.copy();
        this.totalClaimAmount = other.totalClaimAmount == null ? null : other.totalClaimAmount.copy();
        this.caseOfficer = other.caseOfficer == null ? null : other.caseOfficer.copy();
        this.caselawyer = other.caselawyer == null ? null : other.caselawyer.copy();
        this.nextHearingDate = other.nextHearingDate == null ? null : other.nextHearingDate.copy();
        this.amountDepositeInCourt = other.amountDepositeInCourt == null ? null : other.amountDepositeInCourt.copy();
        this.lar = other.lar == null ? null : other.lar.copy();
        this.incCompensation = other.incCompensation == null ? null : other.incCompensation.copy();
        this.amountPaidSLO = other.amountPaidSLO == null ? null : other.amountPaidSLO.copy();
        this.chequeNo = other.chequeNo == null ? null : other.chequeNo.copy();
        this.chequeDate = other.chequeDate == null ? null : other.chequeDate.copy();
        this.appealNo = other.appealNo == null ? null : other.appealNo.copy();
        this.courtAmount = other.courtAmount == null ? null : other.courtAmount.copy();
        this.appealAmount = other.appealAmount == null ? null : other.appealAmount.copy();
        this.appealDate = other.appealDate == null ? null : other.appealDate.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.freefield1 = other.freefield1 == null ? null : other.freefield1.copy();
        this.freefield2 = other.freefield2 == null ? null : other.freefield2.copy();
        this.freefield3 = other.freefield3 == null ? null : other.freefield3.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.hearingId = other.hearingId == null ? null : other.hearingId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CourtCaseCriteria copy() {
        return new CourtCaseCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCaseNo() {
        return caseNo;
    }

    public StringFilter caseNo() {
        if (caseNo == null) {
            caseNo = new StringFilter();
        }
        return caseNo;
    }

    public void setCaseNo(StringFilter caseNo) {
        this.caseNo = caseNo;
    }

    public StringFilter getVillageName() {
        return villageName;
    }

    public StringFilter villageName() {
        if (villageName == null) {
            villageName = new StringFilter();
        }
        return villageName;
    }

    public void setVillageName(StringFilter villageName) {
        this.villageName = villageName;
    }

    public StringFilter getAccuserName() {
        return accuserName;
    }

    public StringFilter accuserName() {
        if (accuserName == null) {
            accuserName = new StringFilter();
        }
        return accuserName;
    }

    public void setAccuserName(StringFilter accuserName) {
        this.accuserName = accuserName;
    }

    public StringFilter getApplicationNo() {
        return applicationNo;
    }

    public StringFilter applicationNo() {
        if (applicationNo == null) {
            applicationNo = new StringFilter();
        }
        return applicationNo;
    }

    public void setApplicationNo(StringFilter applicationNo) {
        this.applicationNo = applicationNo;
    }

    public StringFilter getLandReferenceNo() {
        return landReferenceNo;
    }

    public StringFilter landReferenceNo() {
        if (landReferenceNo == null) {
            landReferenceNo = new StringFilter();
        }
        return landReferenceNo;
    }

    public void setLandReferenceNo(StringFilter landReferenceNo) {
        this.landReferenceNo = landReferenceNo;
    }

    public StringFilter getFirstAppeal() {
        return firstAppeal;
    }

    public StringFilter firstAppeal() {
        if (firstAppeal == null) {
            firstAppeal = new StringFilter();
        }
        return firstAppeal;
    }

    public void setFirstAppeal(StringFilter firstAppeal) {
        this.firstAppeal = firstAppeal;
    }

    public StringFilter getAmount() {
        return amount;
    }

    public StringFilter amount() {
        if (amount == null) {
            amount = new StringFilter();
        }
        return amount;
    }

    public void setAmount(StringFilter amount) {
        this.amount = amount;
    }

    public StringFilter getProjectName() {
        return projectName;
    }

    public StringFilter projectName() {
        if (projectName == null) {
            projectName = new StringFilter();
        }
        return projectName;
    }

    public void setProjectName(StringFilter projectName) {
        this.projectName = projectName;
    }

    public StringFilter getCourtName() {
        return courtName;
    }

    public StringFilter courtName() {
        if (courtName == null) {
            courtName = new StringFilter();
        }
        return courtName;
    }

    public void setCourtName(StringFilter courtName) {
        this.courtName = courtName;
    }

    public StringFilter getDefendantName() {
        return defendantName;
    }

    public StringFilter defendantName() {
        if (defendantName == null) {
            defendantName = new StringFilter();
        }
        return defendantName;
    }

    public void setDefendantName(StringFilter defendantName) {
        this.defendantName = defendantName;
    }

    public StringFilter getCaseDescription() {
        return caseDescription;
    }

    public StringFilter caseDescription() {
        if (caseDescription == null) {
            caseDescription = new StringFilter();
        }
        return caseDescription;
    }

    public void setCaseDescription(StringFilter caseDescription) {
        this.caseDescription = caseDescription;
    }

    public InstantFilter getCaseFilingDate() {
        return caseFilingDate;
    }

    public InstantFilter caseFilingDate() {
        if (caseFilingDate == null) {
            caseFilingDate = new InstantFilter();
        }
        return caseFilingDate;
    }

    public void setCaseFilingDate(InstantFilter caseFilingDate) {
        this.caseFilingDate = caseFilingDate;
    }

    public StringFilter getTotalClaimAmount() {
        return totalClaimAmount;
    }

    public StringFilter totalClaimAmount() {
        if (totalClaimAmount == null) {
            totalClaimAmount = new StringFilter();
        }
        return totalClaimAmount;
    }

    public void setTotalClaimAmount(StringFilter totalClaimAmount) {
        this.totalClaimAmount = totalClaimAmount;
    }

    public StringFilter getCaseOfficer() {
        return caseOfficer;
    }

    public StringFilter caseOfficer() {
        if (caseOfficer == null) {
            caseOfficer = new StringFilter();
        }
        return caseOfficer;
    }

    public void setCaseOfficer(StringFilter caseOfficer) {
        this.caseOfficer = caseOfficer;
    }

    public StringFilter getCaselawyer() {
        return caselawyer;
    }

    public StringFilter caselawyer() {
        if (caselawyer == null) {
            caselawyer = new StringFilter();
        }
        return caselawyer;
    }

    public void setCaselawyer(StringFilter caselawyer) {
        this.caselawyer = caselawyer;
    }

    public InstantFilter getNextHearingDate() {
        return nextHearingDate;
    }

    public InstantFilter nextHearingDate() {
        if (nextHearingDate == null) {
            nextHearingDate = new InstantFilter();
        }
        return nextHearingDate;
    }

    public void setNextHearingDate(InstantFilter nextHearingDate) {
        this.nextHearingDate = nextHearingDate;
    }

    public StringFilter getAmountDepositeInCourt() {
        return amountDepositeInCourt;
    }

    public StringFilter amountDepositeInCourt() {
        if (amountDepositeInCourt == null) {
            amountDepositeInCourt = new StringFilter();
        }
        return amountDepositeInCourt;
    }

    public void setAmountDepositeInCourt(StringFilter amountDepositeInCourt) {
        this.amountDepositeInCourt = amountDepositeInCourt;
    }

    public StringFilter getLar() {
        return lar;
    }

    public StringFilter lar() {
        if (lar == null) {
            lar = new StringFilter();
        }
        return lar;
    }

    public void setLar(StringFilter lar) {
        this.lar = lar;
    }

    public StringFilter getIncCompensation() {
        return incCompensation;
    }

    public StringFilter incCompensation() {
        if (incCompensation == null) {
            incCompensation = new StringFilter();
        }
        return incCompensation;
    }

    public void setIncCompensation(StringFilter incCompensation) {
        this.incCompensation = incCompensation;
    }

    public StringFilter getAmountPaidSLO() {
        return amountPaidSLO;
    }

    public StringFilter amountPaidSLO() {
        if (amountPaidSLO == null) {
            amountPaidSLO = new StringFilter();
        }
        return amountPaidSLO;
    }

    public void setAmountPaidSLO(StringFilter amountPaidSLO) {
        this.amountPaidSLO = amountPaidSLO;
    }

    public StringFilter getChequeNo() {
        return chequeNo;
    }

    public StringFilter chequeNo() {
        if (chequeNo == null) {
            chequeNo = new StringFilter();
        }
        return chequeNo;
    }

    public void setChequeNo(StringFilter chequeNo) {
        this.chequeNo = chequeNo;
    }

    public InstantFilter getChequeDate() {
        return chequeDate;
    }

    public InstantFilter chequeDate() {
        if (chequeDate == null) {
            chequeDate = new InstantFilter();
        }
        return chequeDate;
    }

    public void setChequeDate(InstantFilter chequeDate) {
        this.chequeDate = chequeDate;
    }

    public StringFilter getAppealNo() {
        return appealNo;
    }

    public StringFilter appealNo() {
        if (appealNo == null) {
            appealNo = new StringFilter();
        }
        return appealNo;
    }

    public void setAppealNo(StringFilter appealNo) {
        this.appealNo = appealNo;
    }

    public StringFilter getCourtAmount() {
        return courtAmount;
    }

    public StringFilter courtAmount() {
        if (courtAmount == null) {
            courtAmount = new StringFilter();
        }
        return courtAmount;
    }

    public void setCourtAmount(StringFilter courtAmount) {
        this.courtAmount = courtAmount;
    }

    public StringFilter getAppealAmount() {
        return appealAmount;
    }

    public StringFilter appealAmount() {
        if (appealAmount == null) {
            appealAmount = new StringFilter();
        }
        return appealAmount;
    }

    public void setAppealAmount(StringFilter appealAmount) {
        this.appealAmount = appealAmount;
    }

    public InstantFilter getAppealDate() {
        return appealDate;
    }

    public InstantFilter appealDate() {
        if (appealDate == null) {
            appealDate = new InstantFilter();
        }
        return appealDate;
    }

    public void setAppealDate(InstantFilter appealDate) {
        this.appealDate = appealDate;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getComment() {
        return comment;
    }

    public StringFilter comment() {
        if (comment == null) {
            comment = new StringFilter();
        }
        return comment;
    }

    public void setComment(StringFilter comment) {
        this.comment = comment;
    }

    public StatusFilter getStatus() {
        return status;
    }

    public StatusFilter status() {
        if (status == null) {
            status = new StatusFilter();
        }
        return status;
    }

    public void setStatus(StatusFilter status) {
        this.status = status;
    }

    public StringFilter getFreefield1() {
        return freefield1;
    }

    public StringFilter freefield1() {
        if (freefield1 == null) {
            freefield1 = new StringFilter();
        }
        return freefield1;
    }

    public void setFreefield1(StringFilter freefield1) {
        this.freefield1 = freefield1;
    }

    public StringFilter getFreefield2() {
        return freefield2;
    }

    public StringFilter freefield2() {
        if (freefield2 == null) {
            freefield2 = new StringFilter();
        }
        return freefield2;
    }

    public void setFreefield2(StringFilter freefield2) {
        this.freefield2 = freefield2;
    }

    public StringFilter getFreefield3() {
        return freefield3;
    }

    public StringFilter freefield3() {
        if (freefield3 == null) {
            freefield3 = new StringFilter();
        }
        return freefield3;
    }

    public void setFreefield3(StringFilter freefield3) {
        this.freefield3 = freefield3;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new StringFilter();
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public StringFilter getLastModified() {
        return lastModified;
    }

    public StringFilter lastModified() {
        if (lastModified == null) {
            lastModified = new StringFilter();
        }
        return lastModified;
    }

    public void setLastModified(StringFilter lastModified) {
        this.lastModified = lastModified;
    }

    public LongFilter getHearingId() {
        return hearingId;
    }

    public LongFilter hearingId() {
        if (hearingId == null) {
            hearingId = new LongFilter();
        }
        return hearingId;
    }

    public void setHearingId(LongFilter hearingId) {
        this.hearingId = hearingId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CourtCaseCriteria that = (CourtCaseCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(caseNo, that.caseNo) &&
            Objects.equals(villageName, that.villageName) &&
            Objects.equals(accuserName, that.accuserName) &&
            Objects.equals(applicationNo, that.applicationNo) &&
            Objects.equals(landReferenceNo, that.landReferenceNo) &&
            Objects.equals(firstAppeal, that.firstAppeal) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(projectName, that.projectName) &&
            Objects.equals(courtName, that.courtName) &&
            Objects.equals(defendantName, that.defendantName) &&
            Objects.equals(caseDescription, that.caseDescription) &&
            Objects.equals(caseFilingDate, that.caseFilingDate) &&
            Objects.equals(totalClaimAmount, that.totalClaimAmount) &&
            Objects.equals(caseOfficer, that.caseOfficer) &&
            Objects.equals(caselawyer, that.caselawyer) &&
            Objects.equals(nextHearingDate, that.nextHearingDate) &&
            Objects.equals(amountDepositeInCourt, that.amountDepositeInCourt) &&
            Objects.equals(lar, that.lar) &&
            Objects.equals(incCompensation, that.incCompensation) &&
            Objects.equals(amountPaidSLO, that.amountPaidSLO) &&
            Objects.equals(chequeNo, that.chequeNo) &&
            Objects.equals(chequeDate, that.chequeDate) &&
            Objects.equals(appealNo, that.appealNo) &&
            Objects.equals(courtAmount, that.courtAmount) &&
            Objects.equals(appealAmount, that.appealAmount) &&
            Objects.equals(appealDate, that.appealDate) &&
            Objects.equals(description, that.description) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(status, that.status) &&
            Objects.equals(freefield1, that.freefield1) &&
            Objects.equals(freefield2, that.freefield2) &&
            Objects.equals(freefield3, that.freefield3) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(hearingId, that.hearingId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            caseNo,
            villageName,
            accuserName,
            applicationNo,
            landReferenceNo,
            firstAppeal,
            amount,
            projectName,
            courtName,
            defendantName,
            caseDescription,
            caseFilingDate,
            totalClaimAmount,
            caseOfficer,
            caselawyer,
            nextHearingDate,
            amountDepositeInCourt,
            lar,
            incCompensation,
            amountPaidSLO,
            chequeNo,
            chequeDate,
            appealNo,
            courtAmount,
            appealAmount,
            appealDate,
            description,
            comment,
            status,
            freefield1,
            freefield2,
            freefield3,
            lastModifiedBy,
            lastModified,
            hearingId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CourtCaseCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (caseNo != null ? "caseNo=" + caseNo + ", " : "") +
            (villageName != null ? "villageName=" + villageName + ", " : "") +
            (accuserName != null ? "accuserName=" + accuserName + ", " : "") +
            (applicationNo != null ? "applicationNo=" + applicationNo + ", " : "") +
            (landReferenceNo != null ? "landReferenceNo=" + landReferenceNo + ", " : "") +
            (firstAppeal != null ? "firstAppeal=" + firstAppeal + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (projectName != null ? "projectName=" + projectName + ", " : "") +
            (courtName != null ? "courtName=" + courtName + ", " : "") +
            (defendantName != null ? "defendantName=" + defendantName + ", " : "") +
            (caseDescription != null ? "caseDescription=" + caseDescription + ", " : "") +
            (caseFilingDate != null ? "caseFilingDate=" + caseFilingDate + ", " : "") +
            (totalClaimAmount != null ? "totalClaimAmount=" + totalClaimAmount + ", " : "") +
            (caseOfficer != null ? "caseOfficer=" + caseOfficer + ", " : "") +
            (caselawyer != null ? "caselawyer=" + caselawyer + ", " : "") +
            (nextHearingDate != null ? "nextHearingDate=" + nextHearingDate + ", " : "") +
            (amountDepositeInCourt != null ? "amountDepositeInCourt=" + amountDepositeInCourt + ", " : "") +
            (lar != null ? "lar=" + lar + ", " : "") +
            (incCompensation != null ? "incCompensation=" + incCompensation + ", " : "") +
            (amountPaidSLO != null ? "amountPaidSLO=" + amountPaidSLO + ", " : "") +
            (chequeNo != null ? "chequeNo=" + chequeNo + ", " : "") +
            (chequeDate != null ? "chequeDate=" + chequeDate + ", " : "") +
            (appealNo != null ? "appealNo=" + appealNo + ", " : "") +
            (courtAmount != null ? "courtAmount=" + courtAmount + ", " : "") +
            (appealAmount != null ? "appealAmount=" + appealAmount + ", " : "") +
            (appealDate != null ? "appealDate=" + appealDate + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (comment != null ? "comment=" + comment + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (freefield1 != null ? "freefield1=" + freefield1 + ", " : "") +
            (freefield2 != null ? "freefield2=" + freefield2 + ", " : "") +
            (freefield3 != null ? "freefield3=" + freefield3 + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (hearingId != null ? "hearingId=" + hearingId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
