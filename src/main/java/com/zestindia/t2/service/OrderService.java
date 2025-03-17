package com.zestindia.t2.service;

import com.zestindia.t2.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order saveOrder(Order order);

    void  updateOrder(Order order);

    void  deleteOrder(String  order);

    Optional<Order> getOrder(String id);
    
    List<Order> getAllOrder();

    List<Order> getOrderByUserId(String id );
    
}
