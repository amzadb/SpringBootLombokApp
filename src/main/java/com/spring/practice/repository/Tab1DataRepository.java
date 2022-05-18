package com.spring.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.practice.model.Tab1;

@Repository
public interface Tab1DataRepository extends JpaRepository<Tab1, Integer> {

}
