package org.raddan.newspaper.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.raddan.newspaper.annotation.NotEmpty;

/**
 * @author Alexander Dudkin
 */
@Data
public class ProfileCreateRequest {

    /**
     * Field stands for the fullname of user.
     */
    @NotEmpty(message = "Full name must not be empty.")
    private String fullName;

    /**
     * Field stands for the URL of user's avatar.
     */
    @NotEmpty(message = "Avatar URL must not be empty.")
    private String avatar;

    /**
     * Field stands for the user's bio.
     */
    @NotEmpty(message = "Bio must not be empty.")
    private String bio;
}
