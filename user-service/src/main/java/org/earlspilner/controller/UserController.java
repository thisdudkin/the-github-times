package org.earlspilner.controller;

import lombok.AllArgsConstructor;
import org.earlspilner.dto.UserDto;
import org.earlspilner.models.User;
import org.earlspilner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("Hello, from secured endpoint!");
    }

}
