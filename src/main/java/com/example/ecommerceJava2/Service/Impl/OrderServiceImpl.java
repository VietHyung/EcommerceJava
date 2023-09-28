package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Repository.OrderRepository;
import com.example.ecommerceJava2.Service.OrderService;
import com.example.ecommerceJava2.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        return orderRepository.findOrdersByUser(user);
    }

    @Override
    public float countSum(List<CartItem> orderBaskets) {
        float sum = 0;
        for (CartItem orderBasket : orderBaskets) {
            sum += orderBasket.getQuantity() * orderBasket.getProduct().getPrice();
        }
        return sum;
    }

    @Override
    public void saveOrder(Order orders) {
        orderRepository.save(orders);
    }

    @Override
    public Order getOrder(Long id) {
        Order orders = null;
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) orders = optional.get();
        return orders;
    }

    @Override
    public Order getOrderByUser(User user) throws OrderNotFoundException {
        Order order = orderRepository.findByUser(user);
        if (order == null) {
            throw new OrderNotFoundException("Couldn't find any orders with ID " + order.getOrderId());
        }
        return order;
    }

    @Override
    public void deleteOrder(Long id) throws OrderNotFoundException {
        Long countById = orderRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new OrderNotFoundException("Couldn't find any orders with id " + id);
        }
        orderRepository.deleteById(id);
    }
}
