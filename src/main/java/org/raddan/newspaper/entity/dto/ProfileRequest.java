package org.raddan.newspaper.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Profile request")
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private String bio;
}
