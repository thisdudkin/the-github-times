package org.earlspilner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Alexander Dudkin
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigService {
    public static void main(String[] args) {
        SpringApplication.run(ConfigService.class);
    }
}
