package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.dto.ProfileRequest;
import org.raddan.newspaper.entity.response.ProfileInfoResponse;
import org.raddan.newspaper.entity.response.ProfileCreationResponse;
import org.raddan.newspaper.service.ProfileService;
import org.raddan.newspaper.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    @PostMapping
    public ProfileCreationResponse createProfile(@RequestBody ProfileRequest request) {
        return profileService.createProfile(request);
    }

    @GetMapping
    public ProfileInfoResponse getProfileInfo(@RequestParam String username) {
        return profileService.getProfileInfo(username);
    }

    @PutMapping(path = "/edit")
    public ProfileInfoResponse editProfileInfo(@RequestBody ProfileRequest request) {
        return profileService.editProfileInfo(request);
    }

}
