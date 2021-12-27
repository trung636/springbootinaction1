package com.spring.demo.repository;

import com.spring.demo.model.Order;

public interface OrderRepository {
	Order save(Order order);	
}
