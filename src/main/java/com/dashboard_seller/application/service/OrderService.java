package com.dashboard_seller.application.service;

import com.dashboard_seller.application.dto.AlertResponseDto;
import com.dashboard_seller.domain.model.Order;
import com.dashboard_seller.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private static final String CANCELLED_STATUS = "CANCELLED";
    private static final double ALERT_THRESHOLD = 0.3;

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String status) {
        Order order = new Order(status, LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public double calculateCancelRate(List<Order> orders) {
        if (orders.isEmpty()) {
            return 0.0;
        }

        long cancelledOrders = orders.stream()
                .filter(order -> CANCELLED_STATUS.equalsIgnoreCase(order.getStatus()))
                .count();

        return (double) cancelledOrders / orders.size();
    }

    public boolean isAlert(double cancelRate) {
        return cancelRate > ALERT_THRESHOLD;
    }

    public AlertResponseDto getAlertSummary() {
        List<Order> orders = orderRepository.findAll();
        double cancelRate = calculateCancelRate(orders);
        boolean alert = isAlert(cancelRate);

        return new AlertResponseDto(cancelRate, alert);
    }
}
