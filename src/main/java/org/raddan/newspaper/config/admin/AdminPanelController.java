package org.raddan.newspaper.config.admin;

import lombok.RequiredArgsConstructor;
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

    /**
     * Managing requests from admin panel
     *
     * @param requestInfo display what method should be executed and username of target entity
     * @return {@code ResponseEntity} with information about execution
     */
    @PatchMapping(path = "/panel")
    public ResponseEntity<?> doAction(@RequestBody AdminPanelRequest requestInfo) {
        return adminService.doAction(requestInfo);
    }

}
