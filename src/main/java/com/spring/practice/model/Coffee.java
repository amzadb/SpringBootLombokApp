package com.spring.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "COFFEE_BEVERAGES")
public class Coffee {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "COFFEE_NAME")
	private String name;

	@Column(name = "COFFEE_DESCRIPTION")
	private String description;
}
