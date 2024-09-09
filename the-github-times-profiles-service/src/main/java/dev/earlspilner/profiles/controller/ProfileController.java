package dev.earlspilner.profiles.controller;

import dev.earlspilner.profiles.annotation.CheckUserAccess;
import dev.earlspilner.profiles.dto.ProfileDto;
import dev.earlspilner.profiles.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<ProfileDto> addProfile(@RequestBody ProfileDto profileDto, HttpServletRequest request) {
        return new ResponseEntity<>(profileService.addProfile(request, profileDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable int id) {
        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProfileDto>> getProfiles(Pageable pageable) {
        return new ResponseEntity<>(profileService.getProfiles(pageable), HttpStatus.OK);
    }

    @CheckUserAccess
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable int id, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.updateProfile(id, profileDto), HttpStatus.OK);
    }

    @CheckUserAccess
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id) {
        profileService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
