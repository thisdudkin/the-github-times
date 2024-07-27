package org.raddan.newspaper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ProfileCreateRequest;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/profile")
public class ProfileController {

    private final ProfileService profileService;

    /**
     * Managing POST request from user to create his own profile
     * @param dto data transfer object for user's profile
     * @return {@code ResponseEntity} with information about profile creation
     */
    @PostMapping(path = "/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileCreateRequest dto) {
        return profileService.createProfile(dto);
    }

    /**
     * Managing GET request to get authorized user profile
     * @return Profile Information
     */
    @GetMapping
    public Profile getAuthorizedUserProfile() {
        return profileService.getAuthorizedUserProfile();
    }

    /**
     * Managing PATCH request to update profile fields
     * @param request object that holds new values
     * @return Profile Information
     */
    @PatchMapping(path = "/edit")
    public Profile updateProfile(@Valid @RequestBody ProfileCreateRequest request) {
        return profileService.updateProfile(request);
    }

}
