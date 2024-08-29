package org.earlspilner.auth.service;

import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.advice.IllegalUserCredentialsException;
import org.earlspilner.auth.dto.AuthDto;
import org.earlspilner.auth.dto.Tokens;
import org.earlspilner.auth.dto.UserDto;
import org.earlspilner.auth.security.JwtCore;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServerImpl implements AuthenticationServer {

    private final JwtCore jwtCore;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceClient userServiceClient;
    private final AuthenticationManager authenticationManager;

    @Override
    public Tokens login(AuthDto authDto) {
        try {
            UserDto user = userServiceClient.getUserByUsername(authDto.username());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password()));
            return jwtCore.createTokens(user.username(), user.userRoles());
        } catch (AuthenticationException ex) {
            throw new IllegalUserCredentialsException(ex.getMessage());
        }
    }

    @Override
    public Tokens refresh(String refreshToken) {
        String username = jwtCore.getUsername(refreshToken);
        UserDto user = userServiceClient.getUserByUsername(username);
        if (user != null) {
            return jwtCore.createTokens(user.username(), user.userRoles());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
