package com.bookstore.cartservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCartItemRequest {

    private String bookId;
    private Integer quantity;
}
