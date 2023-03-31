package com.mywebapps.catalogservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponse {
    private String id;

    private String name;
}
