package dev.earlspilner.profiles.service;

import dev.earlspilner.profiles.advice.ProfileNotFoundException;
import dev.earlspilner.profiles.config.FieldUpdater;
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
    private final FieldUpdater fieldUpdater;
    private final ProfileMapper profileMapper;
    private final UserServiceClient userServiceClient;
    private final ProfileRepository profileRepository;

    @Override
    public ProfileDto addProfile(HttpServletRequest request, ProfileDto profileDto) {
        String username = jwtUtil.getUsername(jwtUtil.resolveToken(request));
        UserDto user = userServiceClient.getUserByUsername(username);
        Profile profile = profileMapper.toEntity(profileDto);
        profile.setUserId(user.id());
        profileRepository.save(profile);
        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileDto getProfile(Integer id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID:" + id));
        return profileMapper.toDto(profile);
    }

    @Override
    public Page<ProfileDto> getProfiles(Pageable pageable) {
        Page<Profile> profiles = profileRepository.findAll(pageable);
        return profiles.map(profileMapper::toDto);
    }

    @Override
    public ProfileDto updateProfile(Integer id, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID:" + id));
        fieldUpdater.update(profile, profileDto);
        profileRepository.save(profile);
        return profileMapper.toDto(profile);
    }

    @Override
    public void deleteProfile(Integer id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID:" + id));

        profileRepository.deleteById(profile.getId());
    }

}
