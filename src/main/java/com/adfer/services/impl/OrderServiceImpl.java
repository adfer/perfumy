package com.adfer.services.impl;

import com.adfer.domain.Customer;
import com.adfer.domain.Order;
import com.adfer.domain.OrderDetail;
import com.adfer.domain.OrderHeader;
import com.adfer.repository.CustomerRepository;
import com.adfer.repository.OrderDetailRepository;
import com.adfer.repository.OrderHeaderRepository;
import com.adfer.services.OrderService;
import com.adfer.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by adrianferenc on 07.10.2016.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderHeaderRepository orderHeaderRepository;

    private OrderDetailRepository orderDetailRepository;

    private ShoppingCartService shoppingCartService;

    private CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderHeaderRepository orderHeaderRepository, OrderDetailRepository orderDetailRepository, ShoppingCartService shoppingCartService, CustomerRepository customerRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.shoppingCartService = shoppingCartService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order makeOrder(Customer customer) {
        Customer persCustomer = customerRepository
                .findByFirstNameAndLastName(customer.getFirstName(), customer.getLastName());

        if(persCustomer==null){
            persCustomer = customerRepository.save(customer);
        }

        OrderHeader orderHeader = OrderHeader.builder()
                .customer(persCustomer)
                .build();
        OrderHeader persOrderHeader = orderHeaderRepository.save(orderHeader);

        List<OrderDetail> orderDetailsList = shoppingCartService.getOrderedPerfumes().entrySet().stream()
                .map(e -> OrderDetail.builder()
                        .orderHeaderId(persOrderHeader.getId())
                        .perfume(e.getKey())
                        .quantity(e.getValue())
                        .build()
                )
                .collect(Collectors.toList());

        List<OrderDetail> persOrderDetailsList = new ArrayList<>();
        orderDetailRepository.save(orderDetailsList).forEach(persOrderDetailsList::add);

        Order order = Order.builder()
                .orderHeader(persOrderHeader)
                .orderDetails(persOrderDetailsList)
                .build();

        shoppingCartService.clear();
        return order;
    }

    @Override
    public Order findOrder(Integer orderHeaderId) {
        OrderHeader orderHeader = orderHeaderRepository.findOne(orderHeaderId);
        List<OrderDetail> orderDetailsList = orderDetailRepository.findByOrderHeaderId(orderHeaderId);
        return Order.builder()
                .orderHeader(orderHeader)
                .orderDetails(orderDetailsList)
                .build();
    }

    @Override
    public List<Order> getOrders() {
        List<Order> result = new ArrayList<>();
        Iterator<OrderHeader> iterator = orderHeaderRepository.findAll().iterator();
        while (iterator.hasNext()) {
            OrderHeader orderHeader = iterator.next();
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderHeaderId(orderHeader.getId());
            Order order = Order.builder()
                    .orderHeader(orderHeader)
                    .orderDetails(orderDetails)
                    .build();
            result.add(order);
        }
        return result;
    }

    @Override
    public void removeAllOrders() {
        orderDetailRepository.deleteAll();
        orderHeaderRepository.deleteAll();
    }

    @Override
    public void removeOrderDetail(Integer orderHeaderId, Integer orderDetailId) {
        orderDetailRepository.delete(orderDetailId);
        if (orderDetailRepository.countByOrderHeaderId(orderHeaderId) == 0) {
            orderHeaderRepository.delete(orderHeaderId);
        }
    }
}
