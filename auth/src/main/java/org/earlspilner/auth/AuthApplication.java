package org.earlspilner.auth;

import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.model.AppUser;
import org.earlspilner.auth.model.AppUserRole;
import org.earlspilner.auth.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Dudkin
 */
@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "org.earlspilner.auth.repository")
public class AuthApplication implements CommandLineRunner {

    final UserService userService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
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
