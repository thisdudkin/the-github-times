package org.raddan.newspaper.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Signin Request")
public class SignInRequest {

    @Schema(description = "Username", example = "Brian")
    @Size(min = 3, max = 50, message = "The username must contain between 3 and 255 characters")
    @NotBlank(message = "Username can't be blank")
    private String username;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "The password must contain between 8 and 255 characters")
    private String password;
}
