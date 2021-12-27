package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {
	
//	private RestTemplate rest;
//	
//	@Autowired
//	public HomeController(RestTemplate rest) {
//		this.rest = rest;
//	}


	@GetMapping("/")
	public String home() {
//		rest.delete(null);
		return "home";
	}
	
}
