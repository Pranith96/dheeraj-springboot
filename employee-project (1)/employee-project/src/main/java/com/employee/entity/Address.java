package com.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private String plotNo;
	private String locality;
	private String city;
	private String state;

	@OneToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
}
