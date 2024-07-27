package org.raddan.newspaper.dto;

import lombok.Data;

/**
 * @author Alexander Dudkin
 */
@Data
public class ProfileCreateRequest {

    /**
     * Field stands for the fullname of user.
     */
    private String fullName;

    /**
     * Field stands for the URL of user's avatar.
     */
    private String avatar;

    /**
     * Field stands for the user's bio.
     */
    private String bio;
}
