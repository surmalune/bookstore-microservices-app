package com.bookstore.authservice.controller;

import com.bookstore.authservice.dto.AuthenticateUserRequest;
import com.bookstore.authservice.dto.AuthenticateUserResponse;
import com.bookstore.authservice.service.AuthService;
import com.bookstore.authservice.dto.RegisterUserRequest;
import com.bookstore.authservice.dto.RegisterUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RegisterUserResponse> register(
            @Valid
            @RequestBody RegisterUserRequest registerUserRequest) {
        return authService.register(registerUserRequest)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateUserResponse> authenticate(
            @Valid
            @RequestBody AuthenticateUserRequest authenticateUserRequest) {
        return authService.authenticate(authenticateUserRequest)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }
}
