package com.mywebapps.authservice.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

import static com.mywebapps.authservice.entity.Authority.*;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    READ,
                    UPDATE,
                    DELETE,
                    CREATE
            )
    );

    @Getter
    private final Set<Authority> authorities;
}