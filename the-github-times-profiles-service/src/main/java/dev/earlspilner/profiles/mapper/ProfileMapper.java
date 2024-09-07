package dev.earlspilner.profiles.mapper;

import dev.earlspilner.profiles.dto.ProfileDto;
import dev.earlspilner.profiles.entity.Profile;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toEntity(ProfileDto profileDto);
    ProfileDto toDto(Profile profile);
}
