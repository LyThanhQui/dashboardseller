package com.dashboard_seller.presentation.controller;

import com.dashboard_seller.application.dto.AlertResponseDto;
import com.dashboard_seller.application.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/alert")
    public AlertResponseDto getAlert() {
        return orderService.getAlertSummary();
    }
}
