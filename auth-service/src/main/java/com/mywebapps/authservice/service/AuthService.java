package com.mywebapps.authservice.service;

import com.mywebapps.authservice.dto.AuthenticateUserRequest;
import com.mywebapps.authservice.dto.AuthenticateUserResponse;
import com.mywebapps.authservice.dto.RegisterUserRequest;
import com.mywebapps.authservice.dto.RegisterUserResponse;
import com.mywebapps.authservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Optional<RegisterUserResponse> register(RegisterUserRequest request) {

        User user = (User) userService.createUser(request);

        return Optional.of(RegisterUserResponse.builder()
                                               .id(user.getId())
                                               .username(user.getUsername())
                                               .email(user.getEmail())
                                               .createdAt(user.getCreatedAt())
                                               .roles(user.getRoles())
                                               .build()
        );
    }

    @Transactional
    public Optional<AuthenticateUserResponse> authenticate(AuthenticateUserRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            //return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);

        return Optional.of(new AuthenticateUserResponse(token));
    }
}