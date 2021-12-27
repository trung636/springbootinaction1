package com.spring.demo.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.spring.demo.model.Order;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
	
	private JmsTemplate jms;

	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms) {
		this.jms = jms;
	}

	@Override
	public void sendOrder(Order order) {
		jms.send(session -> session.createObjectMessage((Serializable) order));
	}
}
