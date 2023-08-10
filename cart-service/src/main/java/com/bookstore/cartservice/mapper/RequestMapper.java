package com.bookstore.cartservice.mapper;

import com.bookstore.cartservice.client.BookClient;
import com.bookstore.cartservice.dto.CreateCartItemRequest;
import com.bookstore.cartservice.dto.CreateCartRequest;
import com.bookstore.cartservice.entity.Cart;
import com.bookstore.cartservice.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RequestMapper {

    private final BookClient bookClient;

    public CartItem toCartItem(CreateCartItemRequest request, Cart cart) {
        return CartItem.builder()
                       .cart(cart)
                       .bookId(request.getBookId())
                       .quantity(request.getQuantity())
                       .price(bookClient.getPrice(request.getBookId()).getBody())
                       .build();
    }

    public Cart toCart(CreateCartRequest request, String userId) {
        Cart cart = Cart.builder()
                        .userId(userId)
                        .cartItems(Collections.emptySet())
                        .build();

        cart.setCartItems(request.getCartItems()
                                 .stream().map(item -> this.toCartItem(item, cart))
                                 .collect(Collectors.toSet()));
        return cart;
    }
}
