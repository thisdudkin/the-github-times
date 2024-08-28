package dev.earlspilner.users.controller;

import dev.earlspilner.users.dto.UserDto;
import dev.earlspilner.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.getAllUsers(pageable), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
