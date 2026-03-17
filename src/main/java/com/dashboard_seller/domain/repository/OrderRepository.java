package com.dashboard_seller.domain.repository;

import com.dashboard_seller.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
