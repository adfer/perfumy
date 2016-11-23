package com.adfer.controllers;

import com.adfer.domain.Customer;
import com.adfer.domain.Order;
import com.adfer.services.OrderService;
import com.adfer.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by adrianferenc on 07.10.2016.
 */
@RestController
@RequestMapping("/admin/order")
public class AdminOrderRestController {

    private OrderService orderService;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public AdminOrderRestController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "delete/all", method = RequestMethod.DELETE)
    public void removeOrder() {
        orderService.removeAllOrders();
    }

    @RequestMapping(value = "/detail/delete", method = RequestMethod.DELETE)
    public void removeOrderDetail(@RequestParam Integer orderHeaderId, @RequestParam Integer orderDetailId) {
        orderService.removeOrderDetail(orderHeaderId, orderDetailId);
    }
}
