package com.example.ecommerceJava2.Model;

public enum OrderStatus {
    PENDING, CONFIRMED, REJECTED, DELIVERING;

    public String getOrderStatus() {
        return this.name();
    }
}
