package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Cart;
import com.example.ecommerceJava2.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart WHERE user_id = :userId", nativeQuery = true)
    Cart findbyUserId(Long userId);
}
