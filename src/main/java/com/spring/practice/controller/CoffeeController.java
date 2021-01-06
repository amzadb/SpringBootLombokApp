package com.spring.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.practice.model.Coffee;
import com.spring.practice.model.CoffeeLover;
import com.spring.practice.service.CoffeeService;

@RestController
@RequestMapping("/SpringBootLombokApp")
public class CoffeeController {
	@Autowired
	private CoffeeService coffeeService;
	
	@GetMapping("/coffeesList")
	public ResponseEntity<List<Coffee>> getCoffees() {
		List<Coffee> coffees = coffeeService.getCoffees();
		for (Coffee coffee : coffees) {
			System.out.println(coffee.getName());
		}
		return new ResponseEntity<List<Coffee>>(coffees, HttpStatus.OK);
	}
	
	@GetMapping("/jaffa")
	public ResponseEntity<Void> jaffaCall() {
		System.out.println("******** JAFFA *********");
		CoffeeLover cLover = new CoffeeLover("Amzad", "Cappucino", true);
		System.out.println(cLover.getName());
		System.out.println(cLover.getCoffee());
		System.out.println(cLover.isFavourite());
		System.out.println(cLover.toString());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
