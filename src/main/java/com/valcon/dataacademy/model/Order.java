package com.valcon.dataacademy.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="CustomerOrders")
public class Order {
	
	@Id
	@GeneratedValue
	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	private String customerName;
	
	@OneToMany
	@JoinColumn(name = "orderId")
	private List<OrderItem> items;
	
	@Transient
	private Delivery deliveryDetails;

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
		return deliveryDetails;
	}

	public void setDeliveryDetails(Delivery deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}
	
	
	
	

}
