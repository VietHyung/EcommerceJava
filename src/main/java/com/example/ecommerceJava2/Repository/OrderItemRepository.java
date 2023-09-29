package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.OrderItem;
import com.example.ecommerceJava2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemsByOrder(Order order);
}
