package com.spring.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.practice.model.Tab1;
import com.spring.practice.model.Tab2;
import com.spring.practice.repository.Tab1DataRepository;
import com.spring.practice.repository.Tab2DataRepository;

@Service
public class DataService {

	@Autowired
	private Tab1DataRepository tab1Repository;
	
	@Autowired
	private Tab2DataRepository tab2Repository;


	public List<Tab1> getTab1Data() {
		return tab1Repository.findAll();
	}
	
	public List<Tab2> getTab2Data() {
		return tab2Repository.findAll();
	}
}
