package org.earlspilner.users.rest.advice.custom;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ProfileNotFoundException(String msg) {
        super(msg);
    }
}
