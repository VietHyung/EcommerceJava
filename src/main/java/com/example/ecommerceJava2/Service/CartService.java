package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.User;

public interface CartService {

    public void addProduct(Long productId, Integer quantity, User user);
}
