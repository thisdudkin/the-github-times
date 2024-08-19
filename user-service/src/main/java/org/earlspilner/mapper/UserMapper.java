package org.earlspilner.mapper;

import org.earlspilner.dto.UserDto;
import org.earlspilner.models.User;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring", uses = UserRoleMapper.class)
public interface UserMapper {
    User toUser(UserDto dto);
}
