package org.earlspilner.users.rest.controller;

import org.earlspilner.users.mapper.ProfileMapper;
import org.earlspilner.users.rest.dto.request.ProfileRequest;
import org.earlspilner.users.model.Profile;
import org.earlspilner.users.rest.dto.response.ProfileResponse;
import org.earlspilner.users.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    @Autowired
    public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    @GetMapping
    public ResponseEntity<Page<ProfileResponse>> getProfiles(Pageable pageable) {
        Page<ProfileResponse> profiles = profileService.getProfiles(pageable)
                .map(profileMapper::toResponse);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping(params = "sort")
    public ResponseEntity<List<ProfileResponse>> getProfiles(@RequestParam Sort sort) {
        List<ProfileResponse> profiles = profileService.getProfiles(sort).stream()
                .map(profileMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        List<ProfileResponse> profiles = profileService.getProfiles().stream()
                .map(profileMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable int id) {
        ProfileResponse profile = profileMapper.toResponse(profileService.getProfileById(id));
        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileRequest dto) {
        Profile profile = profileService.createProfile(dto);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int id, @RequestBody ProfileRequest dto) {
        Profile profile = profileService.updateProfile(id, dto);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id) {
        profileService.deleteProfileById(id);
        return ResponseEntity.noContent().build();
    }

}
