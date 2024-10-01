package dev.earlspilner.users.mapper;

import dev.earlspilner.users.dto.UserDto;
import dev.earlspilner.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    UserDto toRegisterResponse(User user);
}
