package com.valcon.dataacademy.controller;

import com.valcon.dataacademy.model.Order;
import com.valcon.dataacademy.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    IOrderService orderService;

    @GetMapping(path="/orders", produces = "application/json")
    public List<Order> getOrders()
    {
        LOGGER.debug("Getting orders");

        return orderService.getOrderList();

    }
}
