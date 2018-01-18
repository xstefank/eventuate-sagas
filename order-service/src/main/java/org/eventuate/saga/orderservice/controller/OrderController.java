package org.eventuate.saga.orderservice.controller;

import org.eventuate.saga.orderservice.model.Order;
import org.eventuate.saga.orderservice.service.OrderService;
import org.learn.eventuate.coreapi.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public String createOrder(@RequestBody ProductInfo productInfo) {
        Order order = orderService.createOrder(productInfo);

        return "Order is being processed - " + order.getId();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> findOne(@PathVariable String id) {
        Order result = orderService.findOne(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
