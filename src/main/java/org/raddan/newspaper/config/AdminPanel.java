package org.raddan.newspaper.config;

import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.raddan.newspaper.enums.Role.*;

/**
 * @author Alexander Dudkin
 */
@Component
public class AdminPanel implements AdminActions {

    @Autowired
    private UserRepository userRepo;


    @Override
    public void ban(User user) {
        user.setIsActive(false);
        userRepo.save(user);
    }

    @Override
    public void unban(User user) {
        user.setIsActive(true);
        userRepo.save(user);
    }

    @Override
    public void grantAdmin(User user) {
        user.setRole(ROLE_ADMIN);
        userRepo.save(user);
    }

    @Override
    public void revokeAdmin(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }

    @Override
    public void grantModerator(User user) {
        user.setRole(ROLE_MODERATOR);
        userRepo.save(user);
    }

    @Override
    public void revokeModerator(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }
}
