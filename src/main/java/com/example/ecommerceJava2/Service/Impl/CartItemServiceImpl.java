package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Repository.CartItemRepository;
import com.example.ecommerceJava2.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem findCartItemById(Long cartItemId) {
        return cartItemRepository.getOne(cartItemId);
    }

    @Override
    public List<CartItem> findCartItemByCartId(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }
}
