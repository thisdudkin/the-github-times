package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.AdminPanelRequest;
import org.raddan.newspaper.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminPanelController {

    private final AdminService adminService;

    @PatchMapping(path = "/panel")
    public ResponseEntity<?> doAction(@RequestBody AdminPanelRequest requestInfo) {
        return adminService.doAction(requestInfo);
    }

}
