package com.techvg.ecourt.service.mapper;

import com.techvg.ecourt.domain.UserAccess;
import com.techvg.ecourt.service.dto.UserAccessDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserAccess} and its DTO {@link UserAccessDTO}.
 */
@Mapper(componentModel = "spring", uses = { SecurityUserMapper.class })
public interface UserAccessMapper extends EntityMapper<UserAccessDTO, UserAccess> {
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "login")
    UserAccessDTO toDto(UserAccess s);
}
