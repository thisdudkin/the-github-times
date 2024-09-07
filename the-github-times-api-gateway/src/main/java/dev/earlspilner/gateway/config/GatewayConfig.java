package dev.earlspilner.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alexander Dudkin
 */
@Configuration
@EnableHystrix
public class GatewayConfig {

    static final String USERS_SERVICE = "http://localhost:9090";
    static final String AUTH_SERVER = "http://localhost:6969";
    static final String PROFILES_SERVICE = "http://localhost:9091";
    static final String ARTICLES_SERVICE = "http://localhost:9093";

    private final AuthenticationFilter filter;

    @Autowired
    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users-service", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(filter))
                        .uri(USERS_SERVICE))
                .route("auth-server", r -> r.path("/api/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri(AUTH_SERVER))
                .route("profiles-service", r -> r.path("/api/profiles/**")
                        .filters(f -> f.filter(filter))
                        .uri(PROFILES_SERVICE))
                .route("articles-service", r -> r.path("/api/articles/**")
                        .filters(f -> f.filter(filter))
                        .uri(ARTICLES_SERVICE))
                .build();
    }

}
