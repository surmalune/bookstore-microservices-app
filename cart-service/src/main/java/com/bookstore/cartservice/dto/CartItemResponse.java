package com.bookstore.cartservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {

    private Long id;
    private Long cartId;
    private String bookId;
    private Integer quantity;
    private BigDecimal price;
}
