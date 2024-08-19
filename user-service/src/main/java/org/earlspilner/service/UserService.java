package org.earlspilner.service;

import org.earlspilner.dto.UserDto;
import org.earlspilner.mapper.UserMapper;
import org.earlspilner.models.User;
import org.earlspilner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserDto dto) {
        User user = userMapper.toUser(dto);
        return userRepository.save(user);
    }

}
