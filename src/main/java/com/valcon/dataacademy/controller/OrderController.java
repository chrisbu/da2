package com.valcon.dataacademy.controller;

import com.valcon.dataacademy.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(path="/orders", produces = "application/json")
    public List<Order> getOrders()
    {
        LOGGER.debug("Getting orders");

        return getDummyOrders();
    }

    private List<Order> getDummyOrders() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setCustomerName("Chris");
        orders.add(order1);

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setCustomerName("Bob");
        orders.add(order2);

        return orders;
    }
}
