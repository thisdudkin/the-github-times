package org.raddan.newspaper.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.raddan.newspaper.config.AdminActions;
import org.raddan.newspaper.config.AdminPanel;
import org.raddan.newspaper.dto.AdminPanelRequest;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final AdminPanel adminPanel;

    @SneakyThrows
    public ResponseEntity<?> doAction(AdminPanelRequest requestInfo) {
        Method method = Arrays.stream(AdminActions.class.getDeclaredMethods())
                .filter(m -> m.getName().equals(requestInfo.getAction()))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Method " + requestInfo.getAction() + " not supported.\nCheck out method name."));

        User user = userRepository.findByUsername(requestInfo.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("User " + requestInfo.getUsername() + " not found."));
        method.invoke(adminPanel, user);
        return new ResponseEntity<>("Method " + requestInfo.getAction() + " executed.", HttpStatus.OK);
    }

}
