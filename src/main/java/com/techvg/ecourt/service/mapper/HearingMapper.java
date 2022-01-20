package com.techvg.ecourt.service.mapper;

import com.techvg.ecourt.domain.Hearing;
import com.techvg.ecourt.service.dto.HearingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hearing} and its DTO {@link HearingDTO}.
 */
@Mapper(componentModel = "spring", uses = { CourtCaseMapper.class })
public interface HearingMapper extends EntityMapper<HearingDTO, Hearing> {
    @Mapping(target = "courtCase", source = "courtCase", qualifiedByName = "id")
    HearingDTO toDto(Hearing s);
}
