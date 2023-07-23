package com.mywebapps.authservice.service;

import com.mywebapps.authservice.dto.RegisterUserRequest;
import com.mywebapps.authservice.entity.Role;
import com.mywebapps.authservice.entity.User;
import com.mywebapps.authservice.exception.EmailAlreadyExistsException;
import com.mywebapps.authservice.exception.UsernameAlreadyExistsException;
import com.mywebapps.authservice.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
@Getter
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new UsernameNotFoundException(
                                     String.format("User '%s' not found", username)
                             ));
    }

    public UserDetails createUser(RegisterUserRequest registerUserRequest)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException
    {
        if (userRepository.existsByUsername(registerUserRequest.getUsername()))
            throw new UsernameAlreadyExistsException(
                    String.format("Username '%s' already exists", registerUserRequest.getUsername())
            );

        if (userRepository.existsByEmail(registerUserRequest.getEmail()))
            throw new EmailAlreadyExistsException(
                    String.format("Email '%s' already exists", registerUserRequest.getEmail())
            );

        User user = User.builder()
                        .username(registerUserRequest.getUsername())
                        .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                        .email(registerUserRequest.getEmail())
                        .createdAt(Instant.now())
                        .roles(Set.of(Role.USER))
                        .build();

        return userRepository.save(user);
    }
}
