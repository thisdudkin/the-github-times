package org.earlspilner.controller;

import lombok.AllArgsConstructor;
import org.earlspilner.dto.UserDto;
import org.earlspilner.model.AuthResponse;
import org.earlspilner.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
