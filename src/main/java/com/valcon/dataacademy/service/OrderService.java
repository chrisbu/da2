package com.valcon.dataacademy.service;

import com.valcon.dataacademy.dao.IOrderRepository;
import com.valcon.dataacademy.exception.InvalidOrderException;
import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;
import com.valcon.dataacademy.model.OrderItem;
import com.valcon.dataacademy.security.ISecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    IOrderRepository orderRepo;

    @Autowired
    ISecurityService securityService;

    IShippingService shippingService;

    @Override
    public List<Order> getOrderList() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        String currentUser = securityService.getLoggedInUser().getUsername();
        LOGGER.info("Order request {} by user {}", id, currentUser);

        Order order = orderRepo.findById(id).get();

        if (order.getCustomerName() != currentUser) {
            throw new RuntimeException("Cannot get order for this user");
        }

        Delivery deliveryDetails = shippingService.getDeliveryDetails(order);
        order.setDeliveryDetails(deliveryDetails);
        return order;
    }

    @Autowired
    public void setShippingService(IShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Override
    public Order saveOrder(Order order) {
        // validate
        if (order.getCustomerName() == null || order.getCustomerName().length() == 0) {
            throw new InvalidOrderException("Order does not have a customer name");
        }

        String currentUser = securityService.getLoggedInUser().getUsername();
        if (order.getCustomerName() != currentUser) {
            throw new InvalidOrderException("Can only create orders for yourself");
        }

        // join the orderItems to the order
        for (OrderItem item : order.getItems()) {
            item.setCustomerOrder(order);
        }

        Order result = this.orderRepo.save(order);
        return result;
    }
}
