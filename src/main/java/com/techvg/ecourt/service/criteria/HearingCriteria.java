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
 * Criteria class for the {@link com.techvg.ecourt.domain.Hearing} entity. This class is used
 * in {@link com.techvg.ecourt.web.rest.HearingResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /hearings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HearingCriteria implements Serializable, Criteria {

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

    private InstantFilter hearingDate;

    private InstantFilter nextHearingDate;

    private StringFilter description;

    private InstantFilter previousHearingDate;

    private StringFilter conclusion;

    private StringFilter comment;

    private StatusFilter status;

    private StringFilter freefield1;

    private StringFilter freefield2;

    private StringFilter freefield3;

    private StringFilter freefield4;

    private StringFilter freefield5;

    private StringFilter lastModifiedBy;

    private StringFilter lastModified;

    private LongFilter courtCaseId;

    private Boolean distinct;

    public HearingCriteria() {}

    public HearingCriteria(HearingCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.hearingDate = other.hearingDate == null ? null : other.hearingDate.copy();
        this.nextHearingDate = other.nextHearingDate == null ? null : other.nextHearingDate.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.previousHearingDate = other.previousHearingDate == null ? null : other.previousHearingDate.copy();
        this.conclusion = other.conclusion == null ? null : other.conclusion.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.freefield1 = other.freefield1 == null ? null : other.freefield1.copy();
        this.freefield2 = other.freefield2 == null ? null : other.freefield2.copy();
        this.freefield3 = other.freefield3 == null ? null : other.freefield3.copy();
        this.freefield4 = other.freefield4 == null ? null : other.freefield4.copy();
        this.freefield5 = other.freefield5 == null ? null : other.freefield5.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.courtCaseId = other.courtCaseId == null ? null : other.courtCaseId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public HearingCriteria copy() {
        return new HearingCriteria(this);
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

    public InstantFilter getHearingDate() {
        return hearingDate;
    }

    public InstantFilter hearingDate() {
        if (hearingDate == null) {
            hearingDate = new InstantFilter();
        }
        return hearingDate;
    }

    public void setHearingDate(InstantFilter hearingDate) {
        this.hearingDate = hearingDate;
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

    public InstantFilter getPreviousHearingDate() {
        return previousHearingDate;
    }

    public InstantFilter previousHearingDate() {
        if (previousHearingDate == null) {
            previousHearingDate = new InstantFilter();
        }
        return previousHearingDate;
    }

    public void setPreviousHearingDate(InstantFilter previousHearingDate) {
        this.previousHearingDate = previousHearingDate;
    }

    public StringFilter getConclusion() {
        return conclusion;
    }

    public StringFilter conclusion() {
        if (conclusion == null) {
            conclusion = new StringFilter();
        }
        return conclusion;
    }

    public void setConclusion(StringFilter conclusion) {
        this.conclusion = conclusion;
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

    public StringFilter getFreefield4() {
        return freefield4;
    }

    public StringFilter freefield4() {
        if (freefield4 == null) {
            freefield4 = new StringFilter();
        }
        return freefield4;
    }

    public void setFreefield4(StringFilter freefield4) {
        this.freefield4 = freefield4;
    }

    public StringFilter getFreefield5() {
        return freefield5;
    }

    public StringFilter freefield5() {
        if (freefield5 == null) {
            freefield5 = new StringFilter();
        }
        return freefield5;
    }

    public void setFreefield5(StringFilter freefield5) {
        this.freefield5 = freefield5;
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

    public LongFilter getCourtCaseId() {
        return courtCaseId;
    }

    public LongFilter courtCaseId() {
        if (courtCaseId == null) {
            courtCaseId = new LongFilter();
        }
        return courtCaseId;
    }

    public void setCourtCaseId(LongFilter courtCaseId) {
        this.courtCaseId = courtCaseId;
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
        final HearingCriteria that = (HearingCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(hearingDate, that.hearingDate) &&
            Objects.equals(nextHearingDate, that.nextHearingDate) &&
            Objects.equals(description, that.description) &&
            Objects.equals(previousHearingDate, that.previousHearingDate) &&
            Objects.equals(conclusion, that.conclusion) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(status, that.status) &&
            Objects.equals(freefield1, that.freefield1) &&
            Objects.equals(freefield2, that.freefield2) &&
            Objects.equals(freefield3, that.freefield3) &&
            Objects.equals(freefield4, that.freefield4) &&
            Objects.equals(freefield5, that.freefield5) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(courtCaseId, that.courtCaseId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            hearingDate,
            nextHearingDate,
            description,
            previousHearingDate,
            conclusion,
            comment,
            status,
            freefield1,
            freefield2,
            freefield3,
            freefield4,
            freefield5,
            lastModifiedBy,
            lastModified,
            courtCaseId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HearingCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (hearingDate != null ? "hearingDate=" + hearingDate + ", " : "") +
            (nextHearingDate != null ? "nextHearingDate=" + nextHearingDate + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (previousHearingDate != null ? "previousHearingDate=" + previousHearingDate + ", " : "") +
            (conclusion != null ? "conclusion=" + conclusion + ", " : "") +
            (comment != null ? "comment=" + comment + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (freefield1 != null ? "freefield1=" + freefield1 + ", " : "") +
            (freefield2 != null ? "freefield2=" + freefield2 + ", " : "") +
            (freefield3 != null ? "freefield3=" + freefield3 + ", " : "") +
            (freefield4 != null ? "freefield4=" + freefield4 + ", " : "") +
            (freefield5 != null ? "freefield5=" + freefield5 + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (courtCaseId != null ? "courtCaseId=" + courtCaseId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
