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
        orderService.setShippingService(getDummyShippingService());

        // Do the call under test
        Order order = orderService.getOrderById(orderId);

        //Check the results
        assertEquals("Test customer", order.getCustomerName());
        assertNotNull(order.getDeliveryDetails());
        assertEquals("88/88/88", order.getDeliveryDetails().getDeliveryDate());

    }

    private IShippingService getDummyShippingService() {
        // anonymous interface
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
