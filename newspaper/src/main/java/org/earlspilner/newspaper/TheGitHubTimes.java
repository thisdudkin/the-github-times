package org.earlspilner.newspaper;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "org.earlspilner.newspaper.*")
@EnableJpaRepositories(basePackages = "org.earlspilner.newspaper.repository")
@EntityScan(basePackages = {"org.earlspilner.newspaper", "org.earlspilner.auth"})
public class TheGitHubTimes {

    public static void main(String[] args) {
        SpringApplication.run(TheGitHubTimes.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
