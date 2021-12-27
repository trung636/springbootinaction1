package com.spring.demo.config;

import com.spring.demo.model.Order;

public interface OrderMessagingService {

	void sendOrder(Order order);
}
