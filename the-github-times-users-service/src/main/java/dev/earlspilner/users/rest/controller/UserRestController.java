package dev.earlspilner.users.rest.controller;

import dev.earlspilner.users.dto.UserDto;
import dev.earlspilner.users.mapper.UserMapper;
import dev.earlspilner.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/users")
public class UserRestController implements UserApi {

    private final UserService userService;

    @Autowired
    public UserRestController(UserMapper userMapper, UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.getUser(username), HttpStatus.OK);
    }

    @Override
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDto>> getUsers(Pageable pageable) {
        return new ResponseEntity<>(this.userService.getUsers(pageable), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.updateUser(username, userDto), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
