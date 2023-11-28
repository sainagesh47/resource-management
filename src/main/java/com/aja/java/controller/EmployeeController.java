package com.aja.java.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aja.java.entity.Employee;
import com.aja.java.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	private static final Logger logger=Logger.getLogger(EmployeeController.class);

	
	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		logger.info(employee);
		Employee emp = employeeService.save(employee);
		return emp;
	}

	@GetMapping("/login/{email}/{password}")
	public String loginEmployee(@PathVariable String email, @PathVariable String password) {
		String message = employeeService.login(email, password);
		return message;
	}

	@GetMapping("/getAllEmployees")
	public List<Employee> getEmployees() {
		List<Employee> allEmployees = employeeService.getAll();
		return allEmployees;
	}

	@GetMapping("/findbyName/{name}")
	public Employee getEmployeeByName(@PathVariable String name) {
		Employee employee = employeeService.findbyName(name);
		return employee;
	}

}
