package org.raddan.newspaper.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.raddan.newspaper.auth.model.JwtAuthenticationResponse;
import org.raddan.newspaper.auth.model.SignInRequest;
import org.raddan.newspaper.auth.model.SignUpRequest;
import org.raddan.newspaper.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Аутентификация")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/signup")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/signin")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
