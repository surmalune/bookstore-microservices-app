package com.bookstore.cartservice.listener;

import com.bookstore.cartservice.dto.Event;
import com.bookstore.cartservice.entity.CartItem;
import com.bookstore.cartservice.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CatalogListener {

    private final CartItemRepository cartItemRepository;

    @KafkaListener(topics = "price-updates", groupId = "cart-service")
    public void listenToPriceUpdates(@Payload Event event, @Headers MessageHeaders headers) {
        log.info("*** change id {} price {}", event.getBookId(), event.getPrice());

        List<CartItem> cartItems = cartItemRepository.findAllByBookId(event.getBookId());
        cartItems.forEach(item -> item.setPrice(event.getPrice()));
        cartItemRepository.saveAll(cartItems);
    }
}
