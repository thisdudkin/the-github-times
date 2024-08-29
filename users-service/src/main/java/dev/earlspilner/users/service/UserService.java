package dev.earlspilner.users.service;

import dev.earlspilner.users.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexander Dudkin
 */
public interface UserService {
    UserDto registerUser(UserDto userDto);
    UserDto getUserByUsername(String username);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto updateUser(String username, UserDto userDto);
    void deleteUser(String username);
}
