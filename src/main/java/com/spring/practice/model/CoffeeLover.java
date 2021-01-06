package com.spring.practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoffeeLover {
	private String name;
	private String coffee;
	private boolean favourite;
}
