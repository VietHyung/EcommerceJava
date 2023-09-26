package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Cart;
import com.example.ecommerceJava2.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "SELECT * FROM cart_item WHERE cart_id = :cartId AND product_id = :productId", nativeQuery = true)
    CartItem findAllByCartIdProdId(Long cartId, Long productId);

}
