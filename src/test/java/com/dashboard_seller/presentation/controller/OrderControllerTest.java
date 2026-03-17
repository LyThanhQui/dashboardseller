package com.dashboard_seller.presentation.controller;

import com.dashboard_seller.application.dto.AlertResponseDto;
import com.dashboard_seller.application.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldReturnAlertResponse() throws Exception {
        when(orderService.getAlertSummary()).thenReturn(new AlertResponseDto(0.4, true));

        mockMvc.perform(get("/alert"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cancelRate").value(0.4))
                .andExpect(jsonPath("$.alert").value(true));
    }
}
