package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.Result;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    public ResponseEntity<Result> getProductById(Long ProductId);
    public ResponseEntity<Result> getProductsByCategoryId(Long categoryId);
}
