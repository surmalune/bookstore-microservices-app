package com.mywebapps.authservice.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameAlreadyExistsException extends AuthenticationException {

    public UsernameAlreadyExistsException(final String msg) {
        super(msg);
    }

}
