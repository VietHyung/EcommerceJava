package com.example.ecommerceJava2.Model.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CategoryDTO {
    private Long categoryId;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
