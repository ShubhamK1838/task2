package com.zestindia.t2.service.impl;


import com.zestindia.t2.dto.convertor.ProductMapper;
import com.zestindia.t2.entity.Order;
import com.zestindia.t2.entity.OrderedProduct;
import com.zestindia.t2.entity.Product;
import com.zestindia.t2.exception.custom.OrderNotFoundException;
import com.zestindia.t2.exception.custom.ProductNotFoundException;
import com.zestindia.t2.repository.OrderRepository;
import com.zestindia.t2.service.OrderService;
import com.zestindia.t2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    @Autowired
    private ProductService productService;


    public OrderServiceImpl(OrderRepository repository) {
        orderRepository = repository;
    }


    @Override
    public Order saveOrder(Order order) {
        order.setId(UUID.randomUUID().toString());
        constructOrder(order);
        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        Order entity = getOrder(order.getId()).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + order.getId()));

        entity.setOrderDate(order.getOrderDate() != null ? order.getOrderDate() : entity.getOrderDate());
        entity.setId(order.getId() != null ? order.getId() : entity.getId());
        entity.setQuantity(order.getQuantity() != 0 ? order.getQuantity() : entity.getQuantity());
        if(order.getProducts()!=null)
            entity.getProducts().addAll(order.getProducts());

        orderRepository.save(entity);


    }

    @Override
    public void deleteOrder(String orderID) {
        Order entity = getOrder(orderID).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderID));

        orderRepository.delete(entity);
    }

    @Override
    public Optional<Order> getOrder(String id) {
        return orderRepository.findById(id);
    }


    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrderByUserId(String id) {
        return orderRepository.findByUserId(id);
    }

    private void constructOrder(Order order) {
        final float[] price = {0};
        List<OrderedProduct> productList = order.getProducts().stream()
                .map((ele) -> {
                    if (ele.getQuantity() <= 0) throw new RuntimeException("Invalid Number of Orders");
                    Product entity = productService.getProduct(ele.getProductId()).orElseThrow(() -> new ProductNotFoundException("The Product Not Available with given Id :" + ele.getId()));
                    if (entity.getQuantity() - ele.getQuantity() < 0)
                        throw new ProductNotFoundException("Product Not Available");
                    order.setTotalPrice(order.getTotalPrice() + (ele.getQuantity() * entity.getPrice()));
                    order.setQuantity((short) (order.getQuantity() + ele.getQuantity()));
                    entity.setQuantity(entity.getQuantity() - ele.getQuantity());
                    OrderedProduct orderedProduct = ProductMapper.toOrderedProduct(entity);
                    orderedProduct.setQuantity(ele.getQuantity());
                    orderedProduct.setId(UUID.randomUUID().toString());
                    return orderedProduct;
                }).collect(Collectors.toList());

        order.setProducts(productList);
    }
}
