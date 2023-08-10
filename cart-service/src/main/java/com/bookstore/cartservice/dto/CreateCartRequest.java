package com.bookstore.cartservice.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCartRequest {

    private Set<CreateCartItemRequest> cartItems;
}
