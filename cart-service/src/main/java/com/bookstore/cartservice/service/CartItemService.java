package com.bookstore.cartservice.service;

import com.bookstore.cartservice.dto.CreateCartItemRequest;
import com.bookstore.cartservice.entity.CartItem;

public interface CartItemService {

    CartItem addCartItem(CreateCartItemRequest request);
}
