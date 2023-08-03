package com.bookstore.catalogservice.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(final String msg) {
        super(msg);
    }
}
