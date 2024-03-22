package com.aja.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aja.java.entity.Employee;
import com.aja.java.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Autowired
	PasswordEncoder passwordEncoder;

	public Employee save(Employee employee) {

		String encryptedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encryptedPassword);
		Employee emp = employeeRepository.save(employee);
		return emp;
	}

	public List<Employee> getAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	public Employee findbyName(String name) {

		Employee employee = employeeRepository.findbyName(name);
		return employee;
	}

	public String login(String email, String password) {

		Employee employee = employeeRepository.findByEmailId(email);
		if (passwordEncoder.matches(password, employee.getPassword()) == true) {
			return "Login Successful";
		}
		return "Login Unsuccessful";

	}

}
