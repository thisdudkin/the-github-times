package org.earlspilner.users.mapper;

import org.earlspilner.users.model.User;
import org.earlspilner.users.rest.dto.response.UserResponse;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}
