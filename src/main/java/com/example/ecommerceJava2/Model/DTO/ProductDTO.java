package com.example.ecommerceJava2.Model.DTO;

import com.example.ecommerceJava2.Model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private String productImage;
    private float price;
    private int quantity;
    private Date createdAt;
    private Date updatedAt;
    private Long categoryId;
    private Category category;

}
