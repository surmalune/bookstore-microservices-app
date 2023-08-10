package com.bookstore.cartservice.controller;

import com.bookstore.cartservice.dto.CartItemResponse;
import com.bookstore.cartservice.dto.CreateCartItemRequest;
import com.bookstore.cartservice.mapper.ResponseMapper;
import com.bookstore.cartservice.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class CartItemController {

    private final CartItemService cartItemService;
    private final ResponseMapper responseMapper;

    @PostMapping
    public ResponseEntity<CartItemResponse> addCartItem(@RequestBody CreateCartItemRequest request) {

        CartItemResponse response = responseMapper.toCartItemResponse(cartItemService.addCartItem(request));

        URI location = MvcUriComponentsBuilder.fromController(getClass())
                                              .path("/{id}")
                                              .buildAndExpand(response.getId())
                                              .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
