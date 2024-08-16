package org.earlspilner.newspaper.rest.controller;

import org.earlspilner.newspaper.model.Profile;
import org.modelmapper.ModelMapper;
import org.earlspilner.newspaper.service.NewspaperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/profiles")
public class ProfileRestController {

    private final NewspaperService newspaperService;
    private final ModelMapper modelMapper;

    public ProfileRestController(NewspaperService newspaperService, ModelMapper modelMapper) {
        this.newspaperService = newspaperService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody Profile profile) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getAllProfiles() {
        return ResponseEntity.ok("My name is - EarlSpilner");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable int id) {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProfile(@RequestParam(required = false) String firstName,
                                           @RequestParam(required = false) String lastName) {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody Profile profile) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable int id) {
        return null;
    }

}
