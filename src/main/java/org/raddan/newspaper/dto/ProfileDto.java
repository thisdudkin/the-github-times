package org.raddan.newspaper.dto;

import lombok.Data;
import org.raddan.newspaper.annotation.NotEmpty;

/**
 * @author Alexander Dudkin
 */
@Data
public class ProfileDto {

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String avatar;

    @NotEmpty
    private String bio;
}
