package com.adfer.services;

import com.adfer.domain.Perfume;
import com.adfer.domain.ShoppingCart;

import java.util.Map;

/**
 * Created by adrianferenc on 06.10.2016.
 */
public interface ShoppingCartService {

    ShoppingCart getShoppingCart();

    ShoppingCart add(Perfume perfume, Integer quantity);

    ShoppingCart clear();

    Map<Perfume,Integer> getOrderedPerfumes();

    int countOrderedQuantity();

    void remove(Integer perfumeId);

    void updateQuantity(Integer perfumeId, Integer newQuantity);
}
