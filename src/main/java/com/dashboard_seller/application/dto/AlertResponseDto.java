package com.dashboard_seller.application.dto;

public class AlertResponseDto {

    private final double cancelRate;
    private final boolean alert;

    public AlertResponseDto(double cancelRate, boolean alert) {
        this.cancelRate = cancelRate;
        this.alert = alert;
    }

    public double getCancelRate() {
        return cancelRate;
    }

    public boolean isAlert() {
        return alert;
    }
}
