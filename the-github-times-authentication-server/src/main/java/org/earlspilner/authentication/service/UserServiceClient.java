package org.earlspilner.authentication.service;

import org.earlspilner.authentication.rest.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Alexander Dudkin
 */
@FeignClient(name = "user-service", url = "http://localhost:9090")
public interface UserServiceClient {

    @GetMapping("/api/users/{username}")
    UserResponse getUserByUsername(@PathVariable("username") String username);

}
