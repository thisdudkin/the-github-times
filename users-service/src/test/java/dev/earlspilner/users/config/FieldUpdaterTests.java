package dev.earlspilner.users.config;

import dev.earlspilner.users.dto.UserDto;
import dev.earlspilner.users.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.earlspilner.users.entity.UserRole.ROLE_ADMIN;
import static dev.earlspilner.users.entity.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alexander Dudkin
 */
public class FieldUpdaterTests {

    private User user;
    private FieldUpdater fieldUpdater;

    @BeforeEach
    void setUp() {
        user = new User(
                null,
                "OldUsername",
                "olduser@gmail.com",
                "MyOldSecretPassword",
                null,
                null,
                List.of(ROLE_USER)
        );

        fieldUpdater = new FieldUpdater();
    }

    @Test
    void testUpdateAllUserFields() {
        UserDto updatedData = new UserDto(
                null,
                "NewUsername",
                "newuser@gmail.com",
                "MyNewSecretPassword",
                null,
                null,
                List.of(ROLE_ADMIN)
        );

        fieldUpdater.update(user, updatedData);

        assertThat(user.getUsername()).isEqualTo("NewUsername");
        assertThat(user.getEmail()).isEqualTo("newuser@gmail.com");
        assertThat(user.getPassword()).isEqualTo("MyNewSecretPassword");
        assertThat(user.getUserRoles()).isNotEqualTo(List.of(ROLE_USER));
    }

    @Test
    void testUpdateSomeUserFields() {
        UserDto updatedData = new UserDto(
                null,
                "NewUsername",
                "newuser@gmail.com",
                null,
                null,
                null,
                null
        );

        fieldUpdater.update(user, updatedData);

        assertThat(user.getUsername()).isEqualTo("NewUsername");
        assertThat(user.getEmail()).isEqualTo("newuser@gmail.com");

        assertThat(user.getPassword()).isEqualTo("MyOldSecretPassword");
        assertThat(user.getUserRoles()).isEqualTo(List.of(ROLE_USER));
    }

}
