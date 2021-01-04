package com.spring.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.practice.model.Coffee;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {

}
