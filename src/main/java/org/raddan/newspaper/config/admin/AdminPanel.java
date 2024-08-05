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
     * Реализация метода {@code ban}
     *
     * @param user определенный пользователь
     */
    @Override
    public void ban(User user) {
        user.setIsActive(false);
        userRepo.save(user);
    }

    /**
     * Реализация метода {@code unban}
     *
     * @param user определенный пользователь
     */
    @Override
    public void unban(User user) {
        user.setIsActive(true);
        userRepo.save(user);
    }

    /**
     * Реализация метода {@code grantAdmin}
     *
     * @param user определенный пользователь
     */
    @Override
    public void grantAdmin(User user) {
        user.setRole(ROLE_ADMIN);
        userRepo.save(user);
    }

    /**
     * Реализация метода {@code revokeAdmin}
     *
     * @param user определенный пользователь
     */
    @Override
    public void revokeAdmin(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }

    /**
     * Реализация метода {@code grantModerator}
     *
     * @param user определенный пользователь
     */
    @Override
    public void grantModerator(User user) {
        user.setRole(ROLE_MODERATOR);
        userRepo.save(user);
    }

    /**
     * Реализация метода {@code revokeModerator}
     *
     * @param user определенный пользователь
     */
    @Override
    public void revokeModerator(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }

    /**
     * Реализация метода {@code grantReporter}
     *
     * @param user определенный пользователь
     */
    @Override
    public void grantReporter(User user) {
        user.setRole(ROLE_REPORTER);
        userRepo.save(user);
    }

    /**
     * Реализация метода {@code revokeReporter}
     *
     * @param user определенный пользователь
     */
    @Override
    public void revokeReporter(User user) {
        user.setRole(ROLE_USER);
        userRepo.save(user);
    }
}
