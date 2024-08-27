package org.earlspilner.authentication.service;

import org.earlspilner.authentication.rest.dto.AuthenticationRequest;
import org.earlspilner.authentication.rest.dto.Tokens;
import org.earlspilner.authentication.rest.dto.UserResponse;
import org.earlspilner.authentication.security.JwtCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtCore jwtCore;
    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(JwtCore jwtCore, UserServiceClient userServiceClient, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtCore = jwtCore;
        this.userServiceClient = userServiceClient;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Tokens login(AuthenticationRequest request) {
        try {
            UserResponse user = userServiceClient.getUserByUsername(request.username());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            return jwtCore.createTokens(user.username(), user.userRoles());
        } catch (AuthenticationException e) {
            // TODO
            throw new RuntimeException("Invalid username/password supplied");
        }
    }


    @Override
    public Tokens refresh(String refreshToken) {
        String username = jwtCore.getUsername(refreshToken);
        UserResponse user = userServiceClient.getUserByUsername(username);
        if (user != null) {
            return jwtCore.createTokens(user.username(), user.userRoles());
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
