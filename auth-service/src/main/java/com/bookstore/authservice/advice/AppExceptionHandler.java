package com.bookstore.authservice.advice;

import com.bookstore.authservice.exception.EmailAlreadyExistsException;
import com.bookstore.authservice.dto.ExceptionResponse;
import com.bookstore.authservice.exception.UsernameAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            UsernameAlreadyExistsException.class
    })
    public ResponseEntity<ExceptionResponse> alreadyExistsException(AuthenticationException exception) {
        log.warn(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> badCredentialsException(BadCredentialsException exception) {
        log.warn(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler({
            UsernameNotFoundException.class,
            NoSuchElementException.class
    })
    public ResponseEntity<ExceptionResponse> usernameNotFoundException(RuntimeException exception) {
        log.warn(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(exception.getMessage()));
    }
}