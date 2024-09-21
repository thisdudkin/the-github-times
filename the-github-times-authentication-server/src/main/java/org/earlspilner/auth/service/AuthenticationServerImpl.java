package org.earlspilner.auth.service;

import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.rest.advice.custom.BadUserCredentialsException;
import org.earlspilner.auth.dto.AuthDto;
import org.earlspilner.auth.dto.Tokens;
import org.earlspilner.auth.dto.UserDto;
import org.earlspilner.auth.feign.UserServiceClient;
import org.earlspilner.auth.security.JwtTokenProvider;
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

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceClient userServiceClient;
    private final AuthenticationManager authenticationManager;

    @Override
    public Tokens login(AuthDto authDto) {
        try {
            UserDto user = userServiceClient.getUserByUsername(authDto.username());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password()));
            return jwtTokenProvider.createTokens(user.username(), user.userRoles());
        } catch (AuthenticationException ex) {
            throw new BadUserCredentialsException(ex.getMessage());
        }
    }

    @Override
    public Tokens refresh(String refreshToken) {
        String username = jwtTokenProvider.getUsername(refreshToken);
        UserDto user = userServiceClient.getUserByUsername(username);
        if (user != null) {
            return jwtTokenProvider.createTokens(user.username(), user.userRoles());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
