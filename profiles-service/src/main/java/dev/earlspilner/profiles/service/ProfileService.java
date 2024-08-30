package dev.earlspilner.profiles.service;

import dev.earlspilner.profiles.dto.ProfileDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexander Dudkin
 */
public interface ProfileService {
    ProfileDto addProfile(HttpServletRequest request, ProfileDto profileDto);
    ProfileDto getProfile(Integer id);
    Page<ProfileDto> getProfiles(Pageable pageable);
    ProfileDto updateProfile(Integer id, ProfileDto profileDto);
    void deleteProfile(Integer id);
}
