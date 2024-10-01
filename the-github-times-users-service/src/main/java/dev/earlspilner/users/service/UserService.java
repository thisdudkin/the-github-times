package dev.earlspilner.users.service;

import dev.earlspilner.users.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexander Dudkin
 */
public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto getUser(String username);
    Page<UserDto> getUsers(Pageable pageable);
    UserDto updateUser(String username, UserDto userDto);
    void deleteUser(Integer id);
}
