package com.bookstore.cartservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    private Long id;
    private String userId;
    private Set<CartItemResponse> cartItems;
    private BigDecimal totalPrice;
}
