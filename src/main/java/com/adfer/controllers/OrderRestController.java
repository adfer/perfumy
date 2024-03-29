package com.adfer.controllers;

import com.adfer.domain.Customer;
import com.adfer.domain.Order;
import com.adfer.services.OrderService;
import com.adfer.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by adrianferenc on 07.10.2016.
 */
@RestController
@RequestMapping("/order")
public class OrderRestController {

    private OrderService orderService;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public OrderRestController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping
    public ResponseEntity<Order> makeOrder(Customer customer) {
        return ResponseEntity.ok(orderService.makeOrder(customer));
    }

}
