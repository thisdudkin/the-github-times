package org.raddan.newspaper.config.admin;

import org.raddan.newspaper.entity.User;

/**
 * @author Alexander Dudkin
 */
public interface AdminActions {

    /**
     * Ban the specific user
     * @param user - specific user
     */
    void ban(User user);

    /**
     * Unban the specific user
     * @param user - specific user
     */
    void unban(User user);

    /**
     * Grant Admin Rights to the specific user
     * @param user - specific user
     */
    void grantAdmin(User user);

    /**
     * Revoke Admin Rights from the specific user
     * @param user - specific user
     */
    void revokeAdmin(User user);

    /**
     * Grant Moderator Rights to the specific user
     * @param user - specific user
     */
    void grantModerator(User user);

    /**
     * Revoke Moderator Rights from the specific user
     * @param user - specific user
     */
    void revokeModerator(User user);
}
