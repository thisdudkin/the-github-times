package dev.earlspilner.users.rest.controller;

import dev.earlspilner.users.mapper.UserMapper;
import dev.earlspilner.users.rest.advice.ExceptionControllerAdvice;
import dev.earlspilner.users.service.ApplicationTestConfig;
import dev.earlspilner.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

}
