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
@Table(name = "TAB2")
public class Tab2 {
	@Id
	@GeneratedValue
	@Column(name = "PROP_ID")
	private Integer propId;
	
	@Column(name = "APP_ID")
	private Integer appId;

	@Column(name = "APP_NAME")
	private String appName;

	@Column(name = "PROP_VALUE")
	private String propValue;
	
}
