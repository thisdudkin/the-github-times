package org.raddan.newspaper.config;

import org.raddan.newspaper.model.User;
import org.raddan.newspaper.enums.DeletionAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Alexander Dudkin
 */
@Component
public class EntityDeletionValidator {

    public boolean isValid(User user) {
        String userRole = user.getRole().name();
        return Arrays.stream(DeletionAuthority.values())
                .map(DeletionAuthority::name)
                .anyMatch(role -> role.equals(userRole));
    }

}
