package dev.earlspilner.profiles.controller;

import dev.earlspilner.profiles.dto.ProfileDto;
import dev.earlspilner.profiles.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto, HttpServletRequest request) {
        return new ResponseEntity<>(profileService.createProfile(request, profileDto), HttpStatus.CREATED);
    }

}
