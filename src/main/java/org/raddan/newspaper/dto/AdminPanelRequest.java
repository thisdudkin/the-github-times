package org.raddan.newspaper.dto;

import lombok.Data;

/**
 * @author Alexander Dudkin
 */
@Data
public class AdminPanelRequest {
    private String action;
    private String username;
}
