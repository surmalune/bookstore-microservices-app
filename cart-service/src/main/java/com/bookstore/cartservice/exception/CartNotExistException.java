package com.bookstore.cartservice.exception;

public class CartNotExistException extends RuntimeException {

    public CartNotExistException(final String msg) {
        super(msg);
    }
}
