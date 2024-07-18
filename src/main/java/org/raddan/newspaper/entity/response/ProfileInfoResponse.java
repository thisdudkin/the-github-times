package org.raddan.newspaper.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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
    private Instant createdAt;
    private Instant updatedAt;
}
