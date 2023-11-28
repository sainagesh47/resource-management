package com.aja.java.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="java_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name = "native")
	private Integer id;
	@Column
	private String name;
	private String userName;
	private String password;
	private String dateOfJoining;
	private Integer noOfDaysInCurrentCompany;
	private String designation;
	private String reportingManager;
	private String previousClient;
	private Integer noOfDaysInBench;
	private String technology;
	private Integer phoneNumber;
	private String emailId;
	private String currentAddress;
	private String permanentAddress;
	private String laptopStatus;
}
