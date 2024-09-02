package dev.earlspilner.users.service;

import dev.earlspilner.users.advice.EmailAlreadyExistsException;
import dev.earlspilner.users.advice.UsernameAlreadyExistsException;
import dev.earlspilner.users.config.FieldUpdater;
import dev.earlspilner.users.dto.UserDto;
import dev.earlspilner.users.entity.User;
import dev.earlspilner.users.mapper.UserMapper;
import dev.earlspilner.users.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final FieldUpdater fieldUpdater;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(@Valid UserDto userDto) {
        if (userRepository.existsByUsername(userDto.username()))
            throw new UsernameAlreadyExistsException("Username is already in use");
        if (userRepository.existsByEmail(userDto.email()))
            throw new EmailAlreadyExistsException("Email is already in use");

        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return userMapper.toDto(user);
    }

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(userMapper::toDto);
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        fieldUpdater.update(existingUser, userDto);
        userRepository.save(existingUser);

        return userMapper.toDto(existingUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        userRepository.deleteById(user.getId());
    }

}
