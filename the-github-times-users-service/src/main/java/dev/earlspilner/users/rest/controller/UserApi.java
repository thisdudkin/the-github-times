package dev.earlspilner.users.rest.controller;

import dev.earlspilner.users.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * @author Alexander Dudkin
 */
public interface UserApi {
    ResponseEntity<UserDto> addUser(UserDto userDto);
    ResponseEntity<UserDto> getUser(String username);
    ResponseEntity<Page<UserDto>> getUsers(Pageable pageable);
    ResponseEntity<UserDto> updateUser(String username, UserDto userDto);
    ResponseEntity<Void> deleteUser(Integer id);
}
