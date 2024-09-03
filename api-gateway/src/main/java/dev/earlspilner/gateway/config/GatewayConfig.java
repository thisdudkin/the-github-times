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
                .build();
    }

}
