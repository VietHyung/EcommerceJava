package com.example.ecommerceJava2.Model.DTO;

import com.example.ecommerceJava2.Model.OrderStatus;

import java.sql.Timestamp;

public class OrderDTO {
    private Long orderId;
    private Long userId;
    private OrderStatus status;
    private String contact;
    private String address;
    private String city;
    private String country;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
