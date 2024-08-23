package org.earlspilner.users.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.earlspilner.users.model.User;

/**
 * @author Alexander Dudkin
 */
@Data
public class ProfileResponse {
    private String id;
    @JsonIgnoreProperties("profile")
    private User user;
    private String name;
    private String bio;
    private String location;
    private String birthDate;
    private String website;
}
