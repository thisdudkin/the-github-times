package org.earlspilner.service;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.earlspilner.models.User;
import org.earlspilner.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.earlspilner.dto.UserDto;
import org.earlspilner.model.AuthResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse register(UserDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException("Email already in use");
        }

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        User registeredUser = restTemplate.postForObject("http://user-service/users", request, User.class);

        String accessToken = jwtUtil.generate(registeredUser.getId(), registeredUser.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(registeredUser.getId(), registeredUser.getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

}
