package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    List<Order> getAllOrdersByUser(User user);

    float countSum(List<CartItem> orderBaskets);

    public void saveOrder(Order orders);

    public Order getOrder(Long id);

    Order getOrderByUser(User user) throws OrderNotFoundException;

    public void deleteOrder(Long id) throws OrderNotFoundException;

}
