package org.earlspilner.auth.controller;

import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.dto.AuthDto;
import org.earlspilner.auth.dto.Tokens;
import org.earlspilner.auth.service.AuthenticationServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationServerController {

    private final AuthenticationServer authenticationServer;

    @PostMapping("/login")
    public ResponseEntity<Tokens> loginUser(@RequestBody AuthDto authDto) {
        return new ResponseEntity<>(authenticationServer.login(authDto), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Tokens> refreshTokens(@RequestParam String refreshToken) {
        return new ResponseEntity<>(authenticationServer.refresh(refreshToken), HttpStatus.CREATED);
    }

}
