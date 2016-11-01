package com.adfer.controllers;

import com.adfer.domain.Perfume;
import com.adfer.domain.ShoppingCart;
import com.adfer.services.PerfumeService;
import com.adfer.services.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@RestController
@RequestMapping("cart")
public class ShoppingCartRestController {

    private Logger LOGGER = LoggerFactory.getLogger(ShoppingCartRestController.class);

    private ShoppingCartService shoppingCartService;

    private PerfumeService perfumeService;

    @Autowired
    public ShoppingCartRestController(ShoppingCartService shoppingCartService, PerfumeService perfumeService) {
        this.shoppingCartService = shoppingCartService;
        this.perfumeService = perfumeService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity addToCart(Integer perfumeId, @RequestParam(defaultValue = "1") int quantity) {
        Perfume perfume = perfumeService.find(perfumeId);
        if (perfume == null) {
            StringBuilder message = new StringBuilder("Perfume with id ").append(perfumeId).append(" not found");
            LOGGER.error(message.toString());
            throw new RuntimeException(message.toString());
        }
        LOGGER.debug("Adding perfume to shopping cart: {}", perfume);
        ShoppingCart shoppingCart = shoppingCartService.add(perfume, quantity);
        return ResponseEntity.ok(shoppingCart.getPerfumes().size());
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity update(Integer perfumeId, @RequestParam(defaultValue = "1") int quantity) {
        Perfume perfume = perfumeService.find(perfumeId);
        if (perfume == null) {
            StringBuilder message = new StringBuilder("Perfume with id ").append(perfumeId).append(" not found");
            LOGGER.error(message.toString());
            throw new RuntimeException(message.toString());
        }
        LOGGER.debug("Adding perfume to shopping cart: {}", perfume);
        shoppingCartService.updateQuantity(perfumeId, quantity);
        return ResponseEntity.ok(shoppingCartService.countOrderedQuantity());
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity removeFromCart(@RequestParam Integer perfumeId) {
        Perfume perfume = perfumeService.find(perfumeId);
        if (perfume == null) {
            StringBuilder message = new StringBuilder("Perfume with id ").append(perfumeId).append(" not found");
            LOGGER.error(message.toString());
            throw new RuntimeException(message.toString());
        }
        LOGGER.debug("Removing perfume from shopping cart: {}", perfume);
        shoppingCartService.remove(perfumeId);
        return ResponseEntity.ok(shoppingCartService.countOrderedQuantity());
    }

    @RequestMapping(value = "clear", method = RequestMethod.DELETE)
    public ResponseEntity clear() {
        LOGGER.debug("Clearing shopping cart");
        shoppingCartService.clear();
        return ResponseEntity.ok(null);
    }


}
