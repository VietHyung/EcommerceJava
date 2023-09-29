package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.OrderItem;

import java.util.List;

public interface OrderItemService {

    public void saveOrderItems(Order newOrder, List<CartItem> orderBaskets);
    public List<OrderItem> findOrderItemsByOrder(Order order);
}
