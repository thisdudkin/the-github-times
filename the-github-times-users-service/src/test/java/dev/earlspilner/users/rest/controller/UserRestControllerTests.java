package dev.earlspilner.users.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.earlspilner.users.dto.UserDto;
import dev.earlspilner.users.mapper.UserMapper;
import dev.earlspilner.users.model.User;
import dev.earlspilner.users.rest.advice.ExceptionControllerAdvice;
import dev.earlspilner.users.service.ApplicationTestConfig;
import dev.earlspilner.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static dev.earlspilner.users.model.UserRole.ROLE_USER;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Alexander Dudkin
 */
@SpringBootTest
@ContextConfiguration(classes = ApplicationTestConfig.class)
@WebAppConfiguration
class UserRestControllerTests {

    @Mock
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRestController userRestController;

    private MockMvc mockMvc;

    @BeforeEach
    void initUsers() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userRestController)
                .setControllerAdvice(new ExceptionControllerAdvice()).build();
    }

    @Test
    void testCreateUserSuccess() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        user.setUserRoles(List.of(ROLE_USER));
        ObjectMapper mapper = new ObjectMapper();
        String newUserAsJSON = mapper.writeValueAsString(userMapper.toDto(user));
        this.mockMvc.perform(post("/users")
                .content(newUserAsJSON).accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

}
