package com.mywebapps.authservice.dto;

import com.mywebapps.authservice.entity.Role;
import lombok.*;

import java.time.Instant;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserResponse {

    private String id;
    private String username;
    private String email;
    private Instant createdAt;
    private Collection<Role> roles;
}
