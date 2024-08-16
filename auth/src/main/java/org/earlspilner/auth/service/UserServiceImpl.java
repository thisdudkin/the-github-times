package org.earlspilner.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.exception.CustomException;
import org.earlspilner.auth.model.AppUser;
import org.earlspilner.auth.repository.UserRepository;
import org.earlspilner.auth.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> register(AppUser appUser) {
        if (!userRepository.existsByUsername(appUser.getUsername())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);

            String token = jwtTokenProvider.createToken(appUser.getUsername(), appUser.getAppUserRoles());
            return ResponseEntity.ok(token);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Void> delete(String username) {
        userRepository.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> search(String username) {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appUser);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> whoami(HttpServletRequest req) {
        AppUser appUser = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        return ResponseEntity.ok(appUser);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> refresh(String username) {
        String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
        return ResponseEntity.ok(token);
    }

    @Override
    public AppUser getAuthorizedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.existsByUsername(username)) {
            return userRepository.findByUsername(username);
        } else {
            throw new CustomException("User is not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

}
