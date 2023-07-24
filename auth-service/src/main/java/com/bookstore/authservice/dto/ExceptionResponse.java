package com.bookstore.authservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
public class ExceptionResponse {

    private String message;
    private Date timestamp;

    public ExceptionResponse(String message) {
        this.message = message;
        this.timestamp = new Date();
    }
}
