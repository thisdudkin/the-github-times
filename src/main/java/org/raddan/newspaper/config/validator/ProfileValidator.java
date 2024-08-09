package org.raddan.newspaper.config.validator;

import org.raddan.newspaper.exception.custom.ProfileNotFoundException;
import org.raddan.newspaper.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Dudkin
 */
@Component
public class ProfileValidator implements Validator<User> {
    @Override
    public void validate(User user) {
        if (user == null || user.getProfile() == null) {
            throw new ProfileNotFoundException("User profile not found");
        }
    }
}
