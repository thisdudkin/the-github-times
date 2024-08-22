package org.earlspilner.users.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.users.rest.dto.request.AuthRequest;
import org.earlspilner.users.rest.dto.request.RegisterRequest;
import org.earlspilner.users.rest.dto.response.Tokens;
import org.earlspilner.users.model.User;
import org.earlspilner.users.repository.UserRepository;
import org.earlspilner.users.rest.advice.custom.CustomException;
import org.earlspilner.users.rest.advice.custom.UnauthorizedException;
import org.earlspilner.users.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.earlspilner.users.model.UserRole.ROLE_USER;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * @author Alexander Dudkin
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Tokens login(AuthRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + request.getUsername()));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return jwtTokenProvider.createTokens(request.getUsername(), user.getUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Tokens register(RegisterRequest request) {
        if (!userRepository.existsByUsername(request.getUsername())) {
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userRoles(List.of(ROLE_USER)).build();
            userRepository.save(user);
            return jwtTokenProvider.createTokens(user.getUsername(), user.getUserRoles());
        } else {
            throw new CustomException("Username is already in use", UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public User search(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found with username: " + username);
        }
        return user.get();
    }

    @Override
    public User whoami(HttpServletRequest request) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    public Tokens refresh(String refreshToken) {
        String username = jwtTokenProvider.getUsername(refreshToken);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return jwtTokenProvider.createTokens(user.get().getUsername(), user.get().getUserRoles());
        } else {
            throw new EntityNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public List<User> getUsers() {
        if (userRepository.count() != 0) {
            return userRepository.findAll();
        } else {
            throw new EntityNotFoundException("Users not found");
        }
    }

    @Override
    public User getAuthenticatedUser(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new UnauthorizedException("User is not authenticated.");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + userDetails.getUsername()));
    }
}
