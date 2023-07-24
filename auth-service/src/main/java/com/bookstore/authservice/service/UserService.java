package com.bookstore.authservice.service;

import com.bookstore.authservice.dto.RegisterUserRequest;
import com.bookstore.authservice.entity.Role;
import com.bookstore.authservice.repository.UserRepository;
import com.bookstore.authservice.entity.User;
import com.bookstore.authservice.exception.EmailAlreadyExistsException;
import com.bookstore.authservice.exception.UsernameAlreadyExistsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        log.info("User with username {} loaded", username);
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new UsernameNotFoundException(
                                     String.format("User '%s' not found", username)
                             ));
    }

    public UserDetails createUser(RegisterUserRequest request)
            throws  UsernameAlreadyExistsException,
                    EmailAlreadyExistsException {

        if (userRepository.existsByUsername(request.getUsername()))
            throw new UsernameAlreadyExistsException(
                    String.format("Username '%s' already exists", request.getUsername())
            );

        if (userRepository.existsByEmail(request.getEmail()))
            throw new EmailAlreadyExistsException(
                    String.format("Email '%s' already exists", request.getEmail())
            );

        log.info("User with username {} registered", request.getUsername());
        User user = User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .email(request.getEmail())
                        .createdAt(Instant.now())
                        .roles(Set.of(Role.USER))   // TODO: set roles
                        .build();

        return userRepository.save(user);
    }
}
