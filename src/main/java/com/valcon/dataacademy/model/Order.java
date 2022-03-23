package com.valcon.dataacademy.model;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CustomerOrders")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    private String customerName;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<OrderItem> items;

    @Transient
    private Delivery deliveryDetails;

    public Order() {
        // noargs constructor
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Delivery getDeliveryDetails() {
        return this.deliveryDetails;
    }

    public void setDeliveryDetails(Delivery deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }
}
