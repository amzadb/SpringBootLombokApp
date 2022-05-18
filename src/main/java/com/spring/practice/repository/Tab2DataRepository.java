package com.spring.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.practice.model.Tab2;

@Repository
public interface Tab2DataRepository extends JpaRepository<Tab2, Integer> {

}
