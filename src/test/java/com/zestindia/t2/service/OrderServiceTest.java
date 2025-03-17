package com.zestindia.t2.service;

import com.zestindia.t2.entity.Order;
import com.zestindia.t2.entity.OrderedProduct;
import com.zestindia.t2.entity.Product;
import com.zestindia.t2.exception.custom.OrderNotFoundException;
import com.zestindia.t2.repository.OrderRepository;
import com.zestindia.t2.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {



    @Mock
    private ProductService productService;


    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderServiceImpl orderService;

    Order order;

    @BeforeEach
    void initOrder() {
        order = Order.builder()
                .id("ID")
                .quantity((short) 4)
                .orderDate(new Date())
                .totalPrice(1234)
                .products(List.of())
                .build();
    }

    @Test
    void saveOrderTest() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        assertNotNull(orderService.saveOrder(order));
    }

    @Test
    void deleteOrderTest() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

        orderService.deleteOrder("ID");

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void deleteOrderTestIfOrderNotExists() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {

            orderService.deleteOrder("ID");
        });
    }

    @Test
    void updateOrderDetailsTestIfOrderExists() {
        var entity = Order.builder()
                .id("OLDID")
                .quantity((short) 2)
                .build();
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(entity));

        orderService.updateOrder(order);

        assertEquals(entity.getId(), order.getId());


    }

    @Test
    void updateOrderDetailsTestIfOrderNotExists() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.updateOrder(order);
        });


    }

    @Test
    void getOrderIfOrderExists() {
        when(orderRepository.findById("ID")).thenReturn(Optional.of(order));

        assertNotNull(orderService.getOrder("ID").orElse(null));
    }

    @Test
    void getAllOrdersTest() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        assertNotNull(orderService.getAllOrder());
    }

    @Test
    void getOrdersByUserIdTest() {
        when(orderRepository.findByUserId("USERID")).thenReturn(List.of(order));

        assertNotNull(orderService.getOrderByUserId("USERID"));

    }


}
