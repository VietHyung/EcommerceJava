package com.example.ecommerceJava2.Model.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class productDTO {
    private Long productId;
    private String name;
    private String description;
    private String productImage;
    private BigDecimal price;
    private int quantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long categoryId;
}
