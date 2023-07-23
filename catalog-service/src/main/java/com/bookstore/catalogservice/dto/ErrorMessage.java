package com.bookstore.catalogservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
public class ErrorMessage {

    private String message;
    private Date timestamp;

    public ErrorMessage(String message) {
        this.message = message;
        this.timestamp = new Date();
    }
}
