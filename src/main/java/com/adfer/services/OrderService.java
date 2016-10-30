package com.adfer.services;

import com.adfer.domain.Customer;
import com.adfer.domain.Order;

import java.util.List;

/**
 * Created by adrianferenc on 07.10.2016.
 */
public interface OrderService {
    Order makeOrder(Customer customer);

    Order findOrder(Integer orderHeaderId);

    List<Order> getOrders();

    void removeAllOrders();

    void removeOrderDetail(Integer orderHeaderId, Integer orderDetailId);
}
