package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ProfileCreateRequest;
import org.raddan.newspaper.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileCreateRequest dto) {
        return profileService.createProfile(dto);
    }

}
