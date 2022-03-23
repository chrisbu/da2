package com.valcon.dataacademy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.valcon.dataacademy.dao.IOrderRepository;
import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;
import com.valcon.dataacademy.service.IShippingService;
import com.valcon.dataacademy.service.OrderService;

@SpringBootTest
public class OrderServiceTest {
	
	// This is the service under test. We're letting spring create it and inject dependencies as required
	@Autowired
	OrderService orderService;

	@Autowired
	@MockBean // this replaces the default implementation with a mock implementation
	IOrderRepository orderDao;
	
	
	@Test
	void testOrderServiceGetOrder() {
		// create an order that will be returned from our mocked out database call
		Long orderId = 100l;
		Order testOrder = new Order();
		testOrder.setOrderId(orderId);
		testOrder.setCustomerName("Test Customer");
		Optional<Order> returnOrder = Optional.of(testOrder); // this is our object that will be returned from the orderDao.findById()
		
		// Mock the orderDao method call - this means we don't call out to the database
		Mockito.when(orderDao.findById(orderId)).thenReturn(returnOrder);
		
		// Inject (method injection) our custom mock shipping service, an alternative to Mockito
		orderService.setShippingService(getDummyShippingService());
		
		// Do the call under test
		Order order = orderService.getOrderById(orderId);
		
		// Check the results.
		assertEquals("Test Customer", order.getCustomerName());
		assertNotNull(order.getDeliveryDetails());
		assertEquals("99/99/9999", order.getDeliveryDetails().getDeliveryDate());
	}
	

	/**
	 * Build and return an implementation of the IShippingService interface
	 * @return
	 */
	IShippingService getDummyShippingService() {
		// anonymous interface
		return new IShippingService() {
			
			@Override
			public Delivery getDeliveryDetails(Order order) {
				// Mock implementation
				Delivery delivery = new Delivery();
				delivery.setDeliveryDate("99/99/9999");
				return delivery;
			}
		};
	}

}
