package com.valcon.dataacademy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valcon.dataacademy.model.Order;
import com.valcon.dataacademy.service.IOrderService;

@RestController
public class OrderController {
	
	// note: always use org.slf4j.Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping(path="/orders", produces = "application/json")
    public List<Order> getOrders() 
    {
		LOGGER.debug("Getting orders");
        return orderService.getOrders();
    }
	
	@GetMapping(path="/orders/{orderIdParam}", produces = "application/json")
	public Order getOrderById(@PathVariable String orderIdParam) {
		LOGGER.debug("Getting order by ID: {}", orderIdParam);
		
		Long orderId = -1l;
		try {
			orderId = Long.valueOf(orderIdParam);
		}
		catch (NumberFormatException ex) {
			LOGGER.error("{} is not of type Long", orderIdParam, ex);
			return null;
		}
		
		return orderService.getOrderById(orderId);
	}

}
