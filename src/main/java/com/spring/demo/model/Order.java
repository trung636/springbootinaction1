package com.spring.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order {
	private static int nextId = 1;

	private Long id;

	private Date placedAt;

	private String name;

	private String street;

	private String city;

	private String state;

	private String zip;

	private String ccNumber;

	private String ccExpiration;

	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<>();

	public void addDesign(Taco saved) {
		this.tacos.add(saved);
	}

}
