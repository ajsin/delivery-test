package com.delivery.api.domain.delivery.repository.orm;

import com.delivery.api.domain.delivery.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}