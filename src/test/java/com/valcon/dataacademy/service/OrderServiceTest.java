package com.valcon.dataacademy.service;

import com.valcon.dataacademy.dao.IOrderRepository;
import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceTest {
    // this is the class under test
    @Autowired
    OrderService orderService;

    @Autowired
    @MockBean
    IOrderRepository mockOrderRepo;

    @Autowired
    @MockBean
    IShippingService mockShippingService;

    @Test
    void testOrderServiceGetOrderById() {
        // create an order that will be returned from our mocked out database call
        Long orderId = 100L;
        Order testOrder = new Order();
        testOrder.setOrderId(orderId);
        testOrder.setCustomerName("Test customer");
        Optional<Order> returnOrder = Optional.of(testOrder); // this is returned by orderRepo.findById()

        // Mock the orderRepo response
        Mockito.when(mockOrderRepo.findById(orderId)).thenReturn(returnOrder);

        // Mock the shippingService response
        Delivery deliveryDetails = new Delivery();
        deliveryDetails.setDeliveryDate("99/99/99");
        Mockito.when(mockShippingService.getDeliveryDetails(ArgumentMatchers.any())).thenReturn(deliveryDetails);

        // Do the call under test
        Order order = orderService.getOrderById(orderId);

        //Check the results
        assertEquals("Test customer", order.getCustomerName());
        assertNotNull(order.getDeliveryDetails());
        assertEquals("99/99/99", order.getDeliveryDetails().getDeliveryDate());

    }
}
