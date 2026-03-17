package com.dashboard_seller.application.service;

import com.dashboard_seller.application.dto.AlertResponseDto;
import com.dashboard_seller.domain.model.Order;
import com.dashboard_seller.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final OrderService orderService = new OrderService(orderRepository);

    @Test
    void shouldReturnAlertTrueWhenCancelRateIsPointFour() {
        when(orderRepository.findAll()).thenReturn(List.of(
                new Order("CANCELLED", LocalDateTime.now()),
                new Order("CANCELLED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now())
        ));

        AlertResponseDto summary = orderService.getAlertSummary();

        assertThat(summary.getCancelRate()).isEqualTo(0.4);
        assertThat(summary.isAlert()).isTrue();
    }

    @Test
    void shouldReturnAlertFalseWhenCancelRateIsPointOne() {
        when(orderRepository.findAll()).thenReturn(List.of(
                new Order("CANCELLED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now()),
                new Order("COMPLETED", LocalDateTime.now())
        ));

        AlertResponseDto summary = orderService.getAlertSummary();

        assertThat(summary.getCancelRate()).isEqualTo(0.1);
        assertThat(summary.isAlert()).isFalse();
    }
}
