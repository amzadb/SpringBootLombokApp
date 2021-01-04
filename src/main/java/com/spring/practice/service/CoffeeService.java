package com.spring.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.practice.model.Coffee;
import com.spring.practice.repository.CoffeeRepository;

@Service
public class CoffeeService {
	@Autowired
	private CoffeeRepository coffeeRepository;
	
	public List<Coffee> getCoffees() {
		return coffeeRepository.findAll();
	}

}
