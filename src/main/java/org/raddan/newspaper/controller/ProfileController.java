package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.dto.ProfileCreationRequest;
import org.raddan.newspaper.entity.response.ProfileCreationResponse;
import org.raddan.newspaper.service.ProfileService;
import org.raddan.newspaper.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    @PostMapping
    public ProfileCreationResponse createProfile(@RequestBody ProfileCreationRequest request) {
        return profileService.createProfile(request);
    }
}
