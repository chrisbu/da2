package com.valcon.dataacademy.service;

import com.valcon.dataacademy.dao.IOrderRepository;
import com.valcon.dataacademy.model.Delivery;
import com.valcon.dataacademy.model.Order;
import com.valcon.dataacademy.model.User;
import com.valcon.dataacademy.security.ISecurityService;
import org.junit.jupiter.api.Test;
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
    ISecurityService mockSecurityService;

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

        // Mock the security service
        User user = new User();
        user.setUsername("Test customer");
        Mockito.when(mockSecurityService.getLoggedInUser()).thenReturn(user);

        // Mock the shippingService response
        orderService.setShippingService(getDummyShippingService());

        // Do the call under test
        Order order = orderService.getOrderById(orderId);

        //Check the results
        assertEquals("Test customer", order.getCustomerName());
        assertNotNull(order.getDeliveryDetails());
        assertEquals("88/88/88", order.getDeliveryDetails().getDeliveryDate());

    }

    @Test
    void testOrderServiceGetOrderById_IncorrectUser() {
        // create an order that will be returned from our mocked out database call
        Long orderId = 100L;
        Order testOrder = new Order();
        testOrder.setOrderId(orderId);
        testOrder.setCustomerName("Test customer");
        Optional<Order> returnOrder = Optional.of(testOrder); // this is returned by orderRepo.findById()

        // Mock the orderRepo response
        Mockito.when(mockOrderRepo.findById(orderId)).thenReturn(returnOrder);

        // Mock the security service
        User user = new User();
        user.setUsername("A different customer");
        Mockito.when(mockSecurityService.getLoggedInUser()).thenReturn(user);

        // Mock the shippingService response
        orderService.setShippingService(getDummyShippingService());

        RuntimeException expectedException = null;
        try {
            // Do the call under test
            Order order = orderService.getOrderById(orderId);
        }
        catch (RuntimeException ex){
            expectedException = ex;
        }

        //Check the results
        assertNotNull(expectedException);
        assertEquals("Cannot get order for this user", expectedException.getMessage());


    }

    private IShippingService getDummyShippingService() {
        // anonymous implementation
        return new IShippingService() {
            // our dummy implementation
            @Override
            public Delivery getDeliveryDetails(Order order) {
                Delivery delivery = new Delivery();
                delivery.setDeliveryDate("88/88/88");
                return delivery;
            }
        };
    }
}
