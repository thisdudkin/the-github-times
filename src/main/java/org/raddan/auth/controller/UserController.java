package org.raddan.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.raddan.auth.dto.UserDataDto;
import org.raddan.auth.dto.UserResponseDto;
import org.raddan.auth.model.AppUser;
import org.raddan.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username,
                                   @RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDataDto user) {
        return userService.register(modelMapper.map(user, AppUser.class));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        return userService.delete(username);
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponseDto search(@PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDto.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public UserResponseDto whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDto.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public ResponseEntity<?> refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
