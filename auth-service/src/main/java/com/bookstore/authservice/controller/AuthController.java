package com.bookstore.authservice.controller;

import com.bookstore.authservice.dto.AuthenticateUserRequest;
import com.bookstore.authservice.dto.AuthenticateUserResponse;
import com.bookstore.authservice.dto.RegisterUserRequest;
import com.bookstore.authservice.dto.RegisterUserResponse;
import com.bookstore.authservice.exception.EmailAlreadyExistsException;
import com.bookstore.authservice.exception.UsernameAlreadyExistsException;
import com.bookstore.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request)
            throws  UsernameAlreadyExistsException,
                    EmailAlreadyExistsException {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateUserResponse> authenticate(@Valid @RequestBody AuthenticateUserRequest request)
            throws  BadCredentialsException,
                    UsernameNotFoundException {

        return ResponseEntity.ok(authService.authenticate(request));
    }
}
