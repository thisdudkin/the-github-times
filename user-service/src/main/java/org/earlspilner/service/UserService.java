package org.earlspilner.service;

import org.earlspilner.dto.UserDto;
import org.earlspilner.mapper.UserMapper;
import org.earlspilner.models.User;
import org.earlspilner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User register(UserDto dto) {
        User user = userMapper.toUser(dto);
        System.out.println("user=" + user.getEmail() + "|role=" + user.getRole() + "|password=" + user.getPassword());
        return userRepository.save(user);
    }

}
