package com.zestindia.t2.controller;

import com.zestindia.t2.entity.Order;
import com.zestindia.t2.exception.custom.OrderNotFoundException;
import com.zestindia.t2.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(order));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> updateOrder() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("We can not Modify The Order After Placed ");

    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteOrderDetails(@PathVariable String id) {
        orderService.deleteOrder(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return
                ResponseEntity.ok().body(
                        orderService.getOrder(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found With Given Id " + id))
                );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUsersOrderDetails(@PathVariable String id) {
        return ResponseEntity.ok().body(orderService.getOrderByUserId(id));
    }

}
