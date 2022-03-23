package com.valcon.dataacademy.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CustomerOrders")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    private String customerName;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderItem> items;

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
}
