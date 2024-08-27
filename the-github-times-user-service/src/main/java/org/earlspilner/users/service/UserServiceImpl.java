package org.earlspilner.users.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.users.mapper.UserMapper;
import org.earlspilner.users.rest.dto.request.AuthRequest;
import org.earlspilner.users.rest.dto.request.RegisterRequest;
import org.earlspilner.users.model.User;
import org.earlspilner.users.repository.UserRepository;
import org.earlspilner.users.rest.advice.custom.CustomException;
import org.earlspilner.users.rest.advice.custom.UnauthorizedException;
import org.earlspilner.users.rest.dto.response.UserResponse;
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
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        if (!userRepository.existsByUsername(request.getUsername())) {
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userRoles(List.of(ROLE_USER)).build();
            userRepository.save(user);
            return userMapper.toResponse(user);
        } else {
            throw new CustomException("Username is already in use", UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserResponse search(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found with username: " + username);
        }
        return userMapper.toResponse(user.get());
    }

    @Override
    public UserResponse whoami(HttpServletRequest request) {
        return null;
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
