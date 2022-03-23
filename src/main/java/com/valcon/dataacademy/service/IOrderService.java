package com.valcon.dataacademy.service;

import java.util.List;

import com.valcon.dataacademy.model.Order;

public interface IOrderService {

	List<Order> getOrders();

	Order getOrderById(Long id);

}