package org.raddan.newspaper.config.admin;

import lombok.Data;
import org.raddan.newspaper.annotation.NotEmpty;

/**
 * @author Alexander Dudkin
 */
@Data
public class AdminPanelRequest {

    /**
     * Field stands for admin action, that needed to be executed.
     */
    @NotEmpty
    private String action;

    /**
     * Field stands for username of target entity
     */
    @NotEmpty
    private String username;
}
