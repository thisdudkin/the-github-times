package org.earlspilner.mapper;

import org.earlspilner.dto.UserDto;
import org.earlspilner.models.User;
import org.earlspilner.models.UserRole;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Dudkin
 */
@Component
public class UserMapper {

    public User toUser(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole() != null ? UserRole.valueOf(dto.getRole().toUpperCase()) : null);

        return user;
    }

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);

        return dto;
    }

}
