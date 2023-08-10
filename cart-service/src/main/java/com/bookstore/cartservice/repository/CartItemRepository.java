package com.bookstore.cartservice.repository;

import com.bookstore.cartservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByBookId(String bookId);
}
