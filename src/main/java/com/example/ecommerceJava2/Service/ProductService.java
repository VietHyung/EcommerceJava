package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.DTO.ProductDTO;
import com.example.ecommerceJava2.Model.Product;

import java.util.List;

public interface ProductService {
    public void saveProduct(Product product);
    public Product getProductById(Long ProductId);
    public List<Product> getProductsByCategoryId(Long categoryId);
}
