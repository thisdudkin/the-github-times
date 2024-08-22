package org.earlspilner.users.mapper;

import org.earlspilner.users.rest.dto.request.ProfileRequest;
import org.earlspilner.users.model.Profile;
import org.earlspilner.users.rest.dto.response.ProfileResponse;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toEntity(ProfileRequest profileRequest);
    ProfileResponse toResponse(Profile profile);
}