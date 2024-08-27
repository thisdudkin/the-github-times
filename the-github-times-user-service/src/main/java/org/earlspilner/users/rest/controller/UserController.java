package org.earlspilner.users.rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.users.rest.dto.request.AuthRequest;
import org.earlspilner.users.rest.dto.request.RegisterRequest;
import org.earlspilner.users.rest.dto.response.Tokens;
import org.earlspilner.users.model.User;
import org.earlspilner.users.rest.dto.response.UserResponse;
import org.earlspilner.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Tokens> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Tokens> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String username) {
        userService.delete(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> search(@PathVariable String username) {
        return ResponseEntity.ok(userService.search(username));
    }

    @GetMapping("/me")
    public ResponseEntity<User> whoami(HttpServletRequest request) {
        return ResponseEntity.ok(userService.whoami(request));
    }

    @GetMapping
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/refresh")
    public ResponseEntity<Tokens> refresh(@RequestParam String refreshToken) {
        return ResponseEntity.ok(userService.refresh(refreshToken));
    }

}
