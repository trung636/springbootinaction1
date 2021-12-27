package com.spring.demo.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Taco {

	private Long id;
	private Date createdAt;
	private String name;
	private List<String> ingredients;

}
