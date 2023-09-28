package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUser(User user);

    List<Order> findOrdersByUser(User user);

    public Long countById(Long OrderId);
}
