package com.spring.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.demo.model.Order;
import com.spring.demo.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private OrderRepository orderRepo;

	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(Model model, Order order) {
		model.addAttribute("order", order);
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@ModelAttribute("order") Order order, SessionStatus sessionStatus) {
		log.info("Order submitted: " + order);
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
