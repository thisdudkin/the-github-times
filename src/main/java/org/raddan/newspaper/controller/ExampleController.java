package org.raddan.newspaper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/example")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class ExampleController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Access only for authenticated users")
    public String example() {
        return "Hello, world!";
    }

    @GetMapping(path = "/admin")
    @Operation(summary = "Access only for authenticated users with role: ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

    @GetMapping(path = "/get-admin")
    @Operation(summary = "Get admin role (for demo)")
    public void getAdmin() {
        userService.getAdmin();
    }

}
