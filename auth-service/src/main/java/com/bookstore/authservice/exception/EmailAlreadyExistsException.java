package com.bookstore.authservice.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailAlreadyExistsException extends AuthenticationException {

    public EmailAlreadyExistsException(final String msg) {
        super(msg);
    }
}
