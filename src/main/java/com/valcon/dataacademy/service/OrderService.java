package com.valcon.dataacademy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.valcon.dataacademy.dao.IOrderRepository;
import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;

@Service
public class OrderService implements IOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	
	// property injection
	@Autowired
	private IOrderRepository orderDao;
	
	private IShippingService shippingService;

	@Autowired
	public void setShippingService(IShippingService shippingService) {
		this.shippingService = shippingService;
	}

	
	@Override
	public List<Order> getOrders() {
		return orderDao.findAll();

	}
	
	@Override
	public Order getOrderById(Long id) {
		LOGGER.debug("Getting order from database by ID: {}", id);	
		Order order = orderDao.findById(id).get();
		
		LOGGER.debug("Retrieved order from db. Enriching with delivery details");
		Delivery delivery = shippingService.getDeliveryDetails(order);
		order.setDeliveryDetails(delivery);
		
		LOGGER.debug("Retrieved delivery details for order {}.  Delivery date: {}", order.getOrderId(), order.getDeliveryDetails().getDeliveryDate());
		return order;
	}

}
