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
     * Управляет запросами из панели администратора
     *
     * @param requestInfo хранит информацию, какой метод должен быть выполнен,
     *                    и никнейм пользователя, над которым выполняется операция
     * @return {@code ResponseEntity} информация о выполнении операции
     */
    @PatchMapping(path = "/panel")
    public ResponseEntity<?> doAction(@RequestBody AdminPanelRequest requestInfo) {
        return adminService.doAction(requestInfo);
    }

}
