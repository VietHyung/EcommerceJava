package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.OrderItem;
import com.example.ecommerceJava2.Repository.OrderItemRepository;
import com.example.ecommerceJava2.Repository.OrderRepository;
import com.example.ecommerceJava2.Service.OrderItemService;
import com.example.ecommerceJava2.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public void saveOrderItems(Order newOrder, List<CartItem> orderBaskets) {
        for (CartItem cartItem : orderBaskets) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public List<OrderItem> findOrderItemsByOrder(Order order) {
        return orderItemRepository.findOrderItemsByOrder(order);
    }
}
