package com.mywebapps.orderservice.repository;

import com.mywebapps.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
