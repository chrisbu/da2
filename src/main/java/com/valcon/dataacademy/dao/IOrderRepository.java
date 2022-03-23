package com.valcon.dataacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valcon.dataacademy.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long>{

	
}
