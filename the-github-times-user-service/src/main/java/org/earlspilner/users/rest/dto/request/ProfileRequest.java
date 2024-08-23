package org.earlspilner.users.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexander Dudkin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String name;
    private String bio;
    private String location;
    private String birthDate;
    private String website;
}
