package org.earlspilner.users.mapper;

import org.earlspilner.users.dto.ProfileDto;
import org.earlspilner.users.model.Profile;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {
//    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    Profile toEntity(ProfileDto profileDto);
    ProfileDto toDto(Profile profile);
}