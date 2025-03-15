package com.zestindia.t2.service;

import com.zestindia.t2.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order saveOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(Order order);

    Optional<Order> getOrder(String id);

    Optional<Order> getOrdersByUserId(String id);

    List<Order> getAllOrder(String id);
    
}
