package com.valcon.dataacademy.service;

import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;

public interface IShippingService {

	Delivery getDeliveryDetails(Order order);

}