package com.bookstore.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(
        name = "cart-service",
        url = "http://catalog:8081/book")
public interface BookClient {

    @GetMapping("/{id}/price")
    ResponseEntity<BigDecimal> getPrice(@PathVariable(name = "id") String id);
}
