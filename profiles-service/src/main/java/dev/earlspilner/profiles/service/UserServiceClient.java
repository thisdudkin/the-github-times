package dev.earlspilner.profiles.service;

import dev.earlspilner.profiles.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Alexander Dudkin
 */
@FeignClient(name = "users-service", url = "http://localhost:9090/api")
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}")
    UserDto getUserByUsername(@PathVariable("username") String username);

}
