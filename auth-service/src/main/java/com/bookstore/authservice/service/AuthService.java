package com.bookstore.authservice.service;

import com.bookstore.authservice.dto.AuthenticateUserRequest;
import com.bookstore.authservice.dto.AuthenticateUserResponse;
import com.bookstore.authservice.dto.RegisterUserRequest;
import com.bookstore.authservice.dto.RegisterUserResponse;
import com.bookstore.authservice.entity.User;
import com.bookstore.authservice.exception.EmailAlreadyExistsException;
import com.bookstore.authservice.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterUserResponse register(RegisterUserRequest request)
            throws  EmailAlreadyExistsException,
                    UsernameAlreadyExistsException {

        User user = (User) userService.createUser(request);     // TODO: should i cast UserDetails to User??

        return RegisterUserResponse.builder()
                                               .id(user.getId())
                                               .username(user.getUsername())
                                               .email(user.getEmail())
                                               .createdAt(user.getCreatedAt())
                                               .roles(user.getRoles())
                                               .build();
    }

    // TODO: Transactional or..? Check best practices
    @Transactional
    public AuthenticateUserResponse authenticate(AuthenticateUserRequest request)
            throws  BadCredentialsException,
                    UsernameNotFoundException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);

        return new AuthenticateUserResponse(token);
    }
}
