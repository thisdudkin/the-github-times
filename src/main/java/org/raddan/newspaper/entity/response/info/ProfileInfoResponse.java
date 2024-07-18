package org.raddan.newspaper.entity.response.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInfoResponse {
    private Long profileId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String bio;
    private String createdAt;
    private String updatedAt;
}
