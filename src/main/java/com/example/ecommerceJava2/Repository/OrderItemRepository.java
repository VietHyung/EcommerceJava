package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
