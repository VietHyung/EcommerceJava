package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.Cart;
import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.User;

public interface CartService {

    public void addProduct(Long productId, Integer quantity, User user);
    public void updateQuantity(Integer quantity, CartItem cItem);
    public void removeCartProduct(Long cartItemId);
    public Cart findCartByUserId(Long userId);
}
