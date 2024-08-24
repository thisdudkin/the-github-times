package org.earlspilner.users.repository;

import org.earlspilner.users.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * @author Alexander Dudkin
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("mySecretPassword");
        userRepository.save(user);
    }

    @Test
    @Order(1)
    @Rollback
    void testExistByUsername() {
        boolean result = userRepository.existsByUsername("username");
        assertThat(result).isTrue();
    }

    @Test
    @Order(2)
    @Rollback
    void testExistByUsernameShouldGiveNegativeAnswer() {
        boolean result = userRepository.existsByUsername("nonExistingUsername");
        assertThat(result).isFalse();
    }

    @Test
    @Order(3)
    @Rollback
    void testSaveAndFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername("username");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("email@gmail.com");
    }

    @Test
    @Order(4)
    @Rollback
    void testDeleteByUsername() {
        userRepository.deleteByUsername("username");
        assertThat(userRepository.existsByUsername("username")).isFalse();
    }


}
