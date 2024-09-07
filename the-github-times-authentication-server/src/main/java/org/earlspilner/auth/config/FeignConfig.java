package org.earlspilner.auth.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alexander Dudkin
 */
@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("User-Agent", "Feign");
        };
    }

}
