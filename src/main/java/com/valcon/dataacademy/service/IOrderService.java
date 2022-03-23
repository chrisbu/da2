package com.valcon.dataacademy.service;

import com.valcon.dataacademy.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrderList();

    Order getOrderById(Long id);

    Order saveOrder(Order order);
}
