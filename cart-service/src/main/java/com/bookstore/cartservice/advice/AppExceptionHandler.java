package com.bookstore.cartservice.advice;

import com.bookstore.cartservice.dto.ErrorMessage;
import com.bookstore.cartservice.exception.CartNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(CartNotExistException.class)
    public ResponseEntity<ErrorMessage> cartNotExistException(CartNotExistException exception) {
        log.warn(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
