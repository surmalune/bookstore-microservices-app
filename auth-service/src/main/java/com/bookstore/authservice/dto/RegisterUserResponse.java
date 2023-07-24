package com.bookstore.authservice.dto;

import com.bookstore.authservice.entity.Role;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserResponse implements Serializable {

    private String id;
    private String username;
    private String email;
    private Instant createdAt;
    private Collection<Role> roles;
}
