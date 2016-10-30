package com.adfer.controllers;

import com.adfer.domain.UserSettings;
import com.adfer.enums.PerfumeCategory;
import com.adfer.services.PerfumeService;
import com.adfer.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Controller
@RequestMapping("cart")
public class ShoppingCartController {

    private final UserSettings settings;
    private PerfumeService perfumeService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(PerfumeService perfumeService, UserSettings settings, ShoppingCartService shoppingCartService) {
        this.perfumeService = perfumeService;
        this.settings = settings;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping
    public String getAll(Model model) {
        model.addAttribute("settings", settings);
        model.addAttribute("shoppingCart", shoppingCartService);
        return "shoppingCart";
    }

}
