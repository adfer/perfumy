package com.adfer.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by adrianferenc on 10.10.2016.
 */
@Builder
@Getter
public class Order {
    private OrderHeader orderHeader;
    private List<OrderDetail> orderDetails;
}
