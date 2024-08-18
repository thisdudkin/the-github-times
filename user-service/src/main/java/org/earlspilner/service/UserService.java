package org.earlspilner.service;

import lombok.AllArgsConstructor;
import org.earlspilner.models.UserVO;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Alexander Dudkin
 */
@Service
@AllArgsConstructor
public class UserService {

    public UserVO save(UserVO userVO) {
        // Simulate save operation
        String userId = String.valueOf(new Date().getTime());
        userVO.setId(userId);
        userVO.setRole("USER");
        // Save user
        return userVO;
    }

}
