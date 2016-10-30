package com.adfer.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Getter
public class ShoppingCart {
    private Map<Integer, Integer> perfumes = new HashMap<>();
}
