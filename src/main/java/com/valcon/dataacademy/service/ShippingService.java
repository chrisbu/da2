package com.valcon.dataacademy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;

@Service
public class ShippingService implements IShippingService {

	
	@Override
	public Delivery getDeliveryDetails(Order order) {
		Delivery delivery = WebClient.builder().build()
				.get()
				.uri("https://dataacademy.free.beeceptor.com/deliveryDate?orderId=" + order.getOrderId())
				.retrieve()
				.bodyToMono(Delivery.class)
				.block();
				
		return delivery;
	}
	
}
