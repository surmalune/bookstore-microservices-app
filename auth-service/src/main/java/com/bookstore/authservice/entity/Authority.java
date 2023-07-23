package com.bookstore.authservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Authority {
    CREATE("create"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete");

    @Getter
    private final String authority;
}
