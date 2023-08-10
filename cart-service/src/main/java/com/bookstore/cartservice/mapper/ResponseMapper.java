package com.bookstore.cartservice.mapper;

import com.bookstore.cartservice.dto.CartItemResponse;
import com.bookstore.cartservice.dto.CartResponse;
import com.bookstore.cartservice.entity.Cart;
import com.bookstore.cartservice.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ResponseMapper {

    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                               .id(cartItem.getId())
                               .cartId(cartItem.getCart().getId())
                               .bookId(cartItem.getBookId())
                               .quantity(cartItem.getQuantity())
                               .price(cartItem.getPrice())
                               .build();
    }

    public CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .cartItems(cart.getCartItems().stream().map(this::toCartItemResponse).collect(Collectors.toSet()))
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}
