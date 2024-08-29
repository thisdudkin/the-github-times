package dev.earlspilner.profiles.service;

import dev.earlspilner.profiles.dto.ProfileDto;
import dev.earlspilner.profiles.dto.UserDto;
import dev.earlspilner.profiles.entity.Profile;
import dev.earlspilner.profiles.mapper.ProfileMapper;
import dev.earlspilner.profiles.repository.ProfileRepository;
import dev.earlspilner.profiles.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final JwtUtil jwtUtil;
    private final ProfileMapper profileMapper;
    private final UserServiceClient userServiceClient;
    private final ProfileRepository profileRepository;

    @Override
    public ProfileDto createProfile(HttpServletRequest request, ProfileDto profileDto) {
        String username = jwtUtil.getUsername(jwtUtil.resolveToken(request));
        UserDto user = userServiceClient.getUserByUsername(username);
        Profile profile = profileMapper.toEntity(profileDto);
        profile.setUserId(user.id());
        profileRepository.save(profile);
        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileDto getProfileById(Integer id) {
        return null;
    }

    @Override
    public Page<ProfileDto> getAllProfiles(Pageable pageable) {
        return null;
    }

    @Override
    public ProfileDto updateProfile(Integer id, ProfileDto profileDto) {
        return null;
    }

    @Override
    public void deleteProfile(Integer id) {

    }

}
