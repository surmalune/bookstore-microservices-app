package com.bookstore.catalogservice.advice;

import com.bookstore.catalogservice.dto.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

//TODO: add MethodArgumentNotValidException, HttpMessageNotReadableException, etc

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> notFoundElementException(NoSuchElementException exception) {
        log.warn(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
