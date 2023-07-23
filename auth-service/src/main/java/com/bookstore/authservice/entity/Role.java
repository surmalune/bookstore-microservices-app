package com.bookstore.authservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    Authority.READ,
                    Authority.UPDATE,
                    Authority.DELETE,
                    Authority.CREATE
            )
    );

    @Getter
    private final Set<Authority> authorities;
}