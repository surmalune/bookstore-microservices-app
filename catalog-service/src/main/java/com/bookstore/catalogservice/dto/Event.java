package com.bookstore.catalogservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    private String bookId;
    private BigDecimal price;
}
