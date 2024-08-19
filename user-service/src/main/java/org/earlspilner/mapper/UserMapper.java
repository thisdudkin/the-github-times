package org.earlspilner.mapper;

import org.earlspilner.dto.UserDto;
import org.earlspilner.models.User;
import org.earlspilner.models.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring", uses = UserRoleMapper.class)
public interface UserMapper {
    @Mapping(source = "role", target = "role")
    User toUser(UserDto dto);
}
