package com.bookstore.cartservice.service.impl;

import com.bookstore.cartservice.dto.CreateCartItemRequest;
import com.bookstore.cartservice.entity.Cart;
import com.bookstore.cartservice.entity.CartItem;
import com.bookstore.cartservice.mapper.RequestMapper;
import com.bookstore.cartservice.repository.CartItemRepository;
import com.bookstore.cartservice.service.CartItemService;
import com.bookstore.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final RequestMapper requestMapper;

    @Override
    public CartItem addCartItem(CreateCartItemRequest request) {

        Cart cart = cartService.getCart().orElseGet(cartService::createCart);

        Optional<CartItem> cartItem = cart.getCartItems().stream()
                                          .filter(item -> item.getBookId().equals(request.getBookId()))
                                          .findFirst();
        cartItem.ifPresent(item -> item.setQuantity(item.getQuantity() + request.getQuantity()));

        return cartItemRepository.save(cartItem.orElseGet(() -> requestMapper.toCartItem(request, cart)));
    }
}
