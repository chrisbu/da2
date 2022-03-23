package com.valcon.dataacademy.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue
	private Long orderItemId;

	@JsonIgnore // don't follow this relationship when serializing to json
	@ManyToOne
    @JoinColumn(name="orderId", nullable=false)
	private Order customerOrder;
	
	private String productName;

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}


	

	public Order getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(Order customerOrder) {
		this.customerOrder = customerOrder;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
	

}
