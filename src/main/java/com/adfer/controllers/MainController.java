package com.adfer.controllers;

import com.adfer.services.AppSettingsService;
import com.adfer.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Controller
public class MainController {

    private OrderService orderService;

    private AppSettingsService appSettingsService;

    @Autowired
    public MainController(OrderService orderService, AppSettingsService appSettingsService) {
        this.orderService = orderService;
        this.appSettingsService = appSettingsService;
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/perfumes/women";
    }

    @RequestMapping("/unavailable")
    public String unavailable() {
        return appSettingsService.isAvailable() ? "redirect:/perfumes/women" : "service_unavailable";
    }


    @RequestMapping("/admin")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("service_available", appSettingsService.isAvailable());
        return "admin/orders";
    }

}

