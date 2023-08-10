package com.bookstore.cartservice.controller;

import com.bookstore.cartservice.dto.CartResponse;
import com.bookstore.cartservice.dto.CreateCartRequest;
import com.bookstore.cartservice.exception.CartNotExistException;
import com.bookstore.cartservice.mapper.ResponseMapper;
import com.bookstore.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class CartController {

    private final CartService cartService;
    private final ResponseMapper responseMapper;

    @PostMapping
    public ResponseEntity<CartResponse> addCart(@RequestBody CreateCartRequest request) {

        CartResponse response = responseMapper.toCartResponse(cartService.addCart(request));

        URI location = MvcUriComponentsBuilder.fromController(getClass())
                                              .path("/{id}")
                                              .buildAndExpand(response.getId())
                                              .toUri();

        return ResponseEntity.created(location).body(response);
    }

    //TODO: if cart is empty, should I return no_content?
    @GetMapping
    public ResponseEntity<CartResponse> getCart()
            throws CartNotExistException {

        return cartService.getCart()
                          .map(responseMapper::toCartResponse)
                          .map(ResponseEntity::ok)
                          .orElseThrow(() ->
                                  new CartNotExistException("User don't have cart yet"));
    }
}
