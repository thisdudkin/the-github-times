package org.raddan.newspaper.dto;

import lombok.Data;

/**
 * @author Alexander Dudkin
 */
@Data
public class AdminPanelRequest {
    /**
     * Field stands for admin action, that needed to be executed.
     */
    private String action;

    /**
     * Field stands for username of target entity
     */
    private String username;
}
