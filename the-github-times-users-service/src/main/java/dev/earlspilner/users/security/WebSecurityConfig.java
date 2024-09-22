package dev.earlspilner.users.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Alexander Dudkin
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf(AbstractHttpConfigurer::disable);

        // No session will be created or used by spring security
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Entry points
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                // Allow requests from Feign Client
                .requestMatchers(request -> {
                    String feignId = request.getHeader("Feign-ID");
                    return feignId != null && feignId.equals(jwtTokenProvider.getSecretKey());
                }).permitAll()
                // Disallow everything else...
                .anyRequest().authenticated());

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling((exception) -> exception
                .accessDeniedPage("/api/users"));

        // Apply JWT
        http.with(new JwtTokenFilterConfigurer(jwtTokenProvider), Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return web -> web.ignoring().requestMatchers("/v2/api-docs")//
                .requestMatchers("/swagger-resources/**")//
                .requestMatchers("/swagger-ui.html")//
                .requestMatchers("/configuration/**")//
                .requestMatchers("/webjars/**")//
                .requestMatchers("/public");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
