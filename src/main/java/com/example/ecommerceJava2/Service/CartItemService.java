package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    public CartItem findCartItemById(Long cartItemId);

    public List<CartItem> findCartItemByCartId(Long userId);
}
