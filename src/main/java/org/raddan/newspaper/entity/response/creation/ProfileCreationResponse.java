package org.raddan.newspaper.entity.response.creation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response on creating new profile")
public class ProfileCreationResponse {
    private Long profileId;
    private String firstName;
    private String lastName;
    private String createdAt;
}
