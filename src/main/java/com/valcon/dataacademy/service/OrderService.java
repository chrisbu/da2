package com.valcon.dataacademy.service;

import com.valcon.dataacademy.dao.IOrderRepository;
import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository orderRepo;

    @Autowired
    IShippingService shippingService;

    @Override
    public List<Order> getOrderList() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderRepo.findById(id).get();

        Delivery deliveryDetails = shippingService.getDeliveryDetails(order);
        order.setDeliveryDetails(deliveryDetails);
        return order;
    }
}
