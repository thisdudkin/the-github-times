package org.raddan.newspaper.config.admin;

import org.raddan.newspaper.entity.User;

/**
 * @author Alexander Dudkin
 */
public interface AdminActions {

    /**
     * Забанить пользователя
     *
     * @param user определенный пользователь
     */
    void ban(User user);

    /**
     * Разбанить пользователя
     *
     * @param user определенный пользователь
     */
    void unban(User user);

    /**
     * Выдать права администратора определенному пользователю
     *
     * @param user определенный пользователь
     */
    void grantAdmin(User user);

    /**
     * Забрать права администратора у определенного пользователя
     *
     * @param user определенный пользователь
     */
    void revokeAdmin(User user);

    /**
     * Выдать права модератора определенному пользователю
     *
     * @param user определенный пользователь
     */
    void grantModerator(User user);

    /**
     * Забрать права модератора у определенного пользователя
     *
     * @param user определенный пользователь
     */
    void revokeModerator(User user);

    /**
     * Выдать права репортера определенному пользователю
     *
     * @param user определенный пользователь
     */
    void grantReporter(User user);

    /**
     * Забрать права репортера у определенного пользователя
     *
     * @param user определенный пользователь
     */
    void revokeReporter(User user);

}
