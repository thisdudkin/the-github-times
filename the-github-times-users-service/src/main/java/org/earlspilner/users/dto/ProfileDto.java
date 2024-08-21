package org.earlspilner.users.dto;

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
public class ProfileDto {
    private String name;
    private String bio;
    private String location;
    private String birthDate;
    private String website;
}
