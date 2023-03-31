package com.mywebapps.catalogservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private String id;

    private String title;

    private String description;

    private BigDecimal price;

    private Set<AuthorResponse> authors;
}
