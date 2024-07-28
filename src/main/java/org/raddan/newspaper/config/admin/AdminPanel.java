package org.raddan.newspaper.config.admin;

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

    /**
     * Implementation of method {@code ban}
     *
     * @param user specific user
     */
    @Override
    public void ban(User user) {
        user.setIsActive(false);
        userRepo.save(user);
    }

    /**
     * Implementation of method {@code unban}
     *
     * @param user specific user
     */
    @Override
    public void unban(User user) {
        user.setIsActive(true);
        userRepo.save(user);
    }

    /**
     * Implementation of method {@code grantAdmin}
     *
     * @param user specific user
     */
    @Override
    public void grantAdmin(User user) {
        user.setRole(ROLE_ADMIN);
        userRepo.save(user);
    }

    /**
     * Implementation of method {@code revokeAdmin}
     *
     * @param user specific user
     */
    @Override
    public void revokeAdmin(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }

    /**
     * Implementation of method {@code grantModerator}
     *
     * @param user specific user
     */
    @Override
    public void grantModerator(User user) {
        user.setRole(ROLE_MODERATOR);
        userRepo.save(user);
    }

    /**
     * Implementation of method {@code revokeModerator}
     *
     * @param user specific user
     */
    @Override
    public void revokeModerator(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }

    /**
     * Implementation of method {@code grantReporter}
     *
     * @param user specific user
     */
    @Override
    public void grantReporter(User user) {
        user.setRole(ROLE_REPORTER);
        userRepo.save(user);
    }

    /**
     * Implementation of method {@code revokeReporter}
     *
     * @param user specific user
     */
    @Override
    public void revokeReporter(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }
}
