package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.Cart;
import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.Product;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Repository.CartItemRepository;
import com.example.ecommerceJava2.Repository.CartRepository;
import com.example.ecommerceJava2.Repository.ProductRepository;
import com.example.ecommerceJava2.Service.CartItemService;
import com.example.ecommerceJava2.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Long productId, Integer quantity, User user) {
        Integer addedQuantity = quantity;

        Product product = productRepository.findProductById(productId);

        Cart cart = cartRepository.findbyUserId(user.getUserId());
        if (cart == null){
            cart = new Cart();
            cart.setUser(user);
        }
        cartRepository.save(cart);

        CartItem cartItem = cartItemRepository.findAllByCartIdProdId(cart.getCartId(),productId);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }
        else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
        }
        cartItemRepository.save(cartItem);
    }

}
