package org.raddan.newspaper.security;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.auth.UserDetailsServiceImpl;
import org.raddan.newspaper.auth.service.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author Alexander Dudkin
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/signup", "/auth/signin")
                        .permitAll()

                        .requestMatchers("/profile", "/profile/create", "/profile/edit", "/profile/delete")
                        .authenticated()

                        .requestMatchers("/profile/{id:[0-9]+}", "/profile/{username:[a-zA-Z]+}")
                        .permitAll()

                        .requestMatchers("/news", "/news/{id}")
                        .permitAll()

                        .requestMatchers("/news/create")
                        .hasAnyRole("ADMIN", "MODERATOR", "REPORTER")

                        .requestMatchers("/news/{id}/edit")
                        .hasAnyRole("ADMIN", "MODERATOR")

                        .requestMatchers("/news/{id}/delete")
                        .hasRole("ADMIN")

                        .requestMatchers("/categories", "/categories/{id}", "/categories/{name}")
                        .permitAll()

                        .requestMatchers("/categories/create")
                        .hasAnyRole("ADMIN", "MODERATOR", "REPORTER")

                        .requestMatchers("/categories/{name}/edit")
                        .hasAnyRole("ADMIN", "MODERATOR")

                        .requestMatchers("/categories/{name}/delete")
                        .hasRole("ADMIN")

                        .requestMatchers("/tags", "/tags/{id}", "/tags/{name}")
                        .permitAll()

                        .requestMatchers("/tags/create")
                        .hasAnyRole("ADMIN", "MODERATOR", "REPORTER")

                        .requestMatchers("/tags/{name}/edit")
                        .hasAnyRole("ADMIN", "MODERATOR")

                        .requestMatchers("/tags/{name}/delete")
                        .hasRole("ADMIN")

                        .requestMatchers("/admin/**")
                        .permitAll()

                        .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}