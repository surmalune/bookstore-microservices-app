package com.bookstore.cartservice.service.impl;

import com.bookstore.cartservice.dto.CreateCartRequest;
import com.bookstore.cartservice.entity.Cart;
import com.bookstore.cartservice.entity.CartItem;
import com.bookstore.cartservice.mapper.RequestMapper;
import com.bookstore.cartservice.repository.CartRepository;
import com.bookstore.cartservice.service.CartService;
import com.bookstore.cartservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final RequestMapper requestMapper;
    private final JwtUtil jwtUtil;

    @Override
    public Cart addCart(CreateCartRequest request) {
        String userId = jwtUtil.getUserIdFromToken();

        Optional<Cart> cart = cartRepository.findByUserId(userId);

        // if cartitem already exist in cart, then change quantity,
        // else add cartitem
        cart.ifPresent(c -> request.getCartItems()
                                   .forEach(item -> {

            Optional<CartItem> cartItem = c.getCartItems().stream()
                                                       .filter(i -> i.getBookId().equals(item.getBookId()))
                                                       .findFirst();

            cartItem.ifPresentOrElse(i -> i.setQuantity(i.getQuantity() + item.getQuantity()),
                    () -> c.getCartItems().add(requestMapper.toCartItem(item, c)));
        }));

        return cartRepository.save(cart.orElseGet(() -> requestMapper.toCart(request, userId)));
    }

    @Override
    public Cart createCart() {
        String userId = jwtUtil.getUserIdFromToken();
        return cartRepository.save(Cart.builder()
                                       .userId(userId)
                                       .cartItems(Collections.emptySet())
                                       .build());
    }

    @Override
    public Optional<Cart> getCart() {
        String userId = jwtUtil.getUserIdFromToken();
        return cartRepository.findByUserId(userId);
    }
}
