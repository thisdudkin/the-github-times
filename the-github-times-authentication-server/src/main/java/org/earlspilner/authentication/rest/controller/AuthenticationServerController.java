package org.earlspilner.authentication.rest.controller;

import org.earlspilner.authentication.rest.dto.AuthenticationRequest;
import org.earlspilner.authentication.rest.dto.Tokens;
import org.earlspilner.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationServerController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationServerController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Tokens> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Tokens> refresh(@RequestParam String refreshToken) {
        return ResponseEntity.ok(authenticationService.refresh(refreshToken));
    }

}
