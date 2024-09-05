package dev.earlspilner.users;

import dev.earlspilner.users.entity.User;
import dev.earlspilner.users.mapper.UserMapper;
import dev.earlspilner.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;

import static dev.earlspilner.users.entity.UserRole.*;

/**
 * @author Alexander Dudkin
 */
@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class UserServiceApplication implements CommandLineRunner {

    final UserMapper userMapper;
    final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User client = new User();
        client.setUsername("client");
        client.setPassword("client");
        client.setEmail("client@email.com");
        client.setUserRoles(List.of(ROLE_USER));

        userService.registerUser(userMapper.toDto(client));

        User moderator = new User();
        moderator.setUsername("moderator");
        moderator.setPassword("moderator");
        moderator.setEmail("moderator@email.com");
        moderator.setUserRoles(List.of(ROLE_MODERATOR));

        userService.registerUser(userMapper.toDto(moderator));

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@email.com");
        admin.setUserRoles(List.of(ROLE_ADMIN));

        userService.registerUser(userMapper.toDto(admin));
    }
}
