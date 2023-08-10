package com.bookstore.cartservice.service;

import com.bookstore.cartservice.dto.CreateCartRequest;
import com.bookstore.cartservice.entity.Cart;

import java.util.Optional;

public interface CartService {

    Optional<Cart> getCart();
    Cart addCart(CreateCartRequest request);
    Cart createCart();
}
