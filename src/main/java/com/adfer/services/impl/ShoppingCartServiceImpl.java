package com.adfer.services.impl;

import com.adfer.domain.Perfume;
import com.adfer.domain.ShoppingCart;
import com.adfer.services.PerfumeService;
import com.adfer.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by adrianferenc on 06.10.2016.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCart shoppingCart;

    private PerfumeService perfumeService;

    @Autowired
    public ShoppingCartServiceImpl(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
        this.shoppingCart = new ShoppingCart();
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public ShoppingCart add(Perfume perfume, Integer quantity) {
        shoppingCart.getPerfumes()
                .compute(perfume.getId(), (k, v) -> v == null ? quantity : v + quantity);
        return shoppingCart;
    }

    @Override
    public ShoppingCart clear() {
        shoppingCart.getPerfumes().clear();
        return shoppingCart;
    }

    @Override
    public Map<Perfume, Integer> getOrderedPerfumes() {
        Map<Perfume, Integer> map = shoppingCart
                .getPerfumes()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> perfumeService.find(e.getKey()), e -> e.getValue()));
        return map;
    }

    @Override
    public int countOrderedQuantity() {
        return shoppingCart.getPerfumes().values().stream().reduce((i1, i2) -> i1 + i2).orElse(0);
    }

    @Override
    public void remove(Integer perfumeId) {
        shoppingCart.getPerfumes().remove(perfumeId);
    }

    @Override
    public void updateQuantity(Integer perfumeId, Integer newQuantity) {
        shoppingCart.getPerfumes().put(perfumeId, newQuantity);
    }
}
