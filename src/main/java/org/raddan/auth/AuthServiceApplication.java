package org.raddan.auth;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.raddan.auth.model.AppUser;
import org.raddan.auth.model.AppUserRole;
import org.raddan.auth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Dudkin
 */
@SpringBootApplication
@RequiredArgsConstructor
public class AuthServiceApplication implements CommandLineRunner {

    final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... params) throws Exception {
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@email.com");
        admin.setAppUserRoles(new ArrayList<AppUserRole>(List.of(AppUserRole.ROLE_ADMIN)));

        userService.register(admin);

        AppUser client = new AppUser();
        client.setUsername("client");
        client.setPassword("client");
        client.setEmail("client@email.com");
        client.setAppUserRoles(new ArrayList<AppUserRole>(List.of(AppUserRole.ROLE_CLIENT)));

        userService.register(client);
    }

}
