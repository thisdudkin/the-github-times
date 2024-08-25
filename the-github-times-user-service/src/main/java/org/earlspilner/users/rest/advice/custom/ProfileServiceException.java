package org.earlspilner.users.rest.advice.custom;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class ProfileServiceException extends RuntimeException {
    public ProfileServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ProfileServiceException(String msg) {
        super(msg);
    }
}
