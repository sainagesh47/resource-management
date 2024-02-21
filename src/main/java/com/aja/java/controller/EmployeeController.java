package com.aja.java.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aja.java.dto.EmployeeDto;
import com.aja.java.dto.EmployeeDto2;
import com.aja.java.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ObjectMapper mapper;
	
	private static final Logger logger=Logger.getLogger(EmployeeController.class);

	
//Saving Employee with Employee object and Image
	@PostMapping("/saveEmployee")
	public String saveEmployee(@RequestParam("employeeData") String employeeData,
							   @RequestParam("employeeImage") MultipartFile imageFile) {
		
		EmployeeDto employeeDto = new EmployeeDto();
		try { 
			employeeDto = mapper.readValue(employeeData, EmployeeDto.class);
		}
		catch (JsonProcessingException e) {
			return "Encountering json parsing error";
		}
		String employeeSavingInfo= employeeService.save(employeeDto,imageFile);
		return employeeSavingInfo;
	}
	
	
//  getting Employee by name 
	@GetMapping("/login/{email}/{password}")
	public String loginEmployee(@PathVariable String email, @PathVariable String password) {
		String message = employeeService.login(email, password);
		return message;
	}
	
	
	//getting all the Employees
	@GetMapping("/getAllEmployees")
	public List<EmployeeDto2> getEmployees() {
		List<EmployeeDto2> allEmployees = employeeService.getAll();
		return allEmployees;
	}
	
	
//  Logging Employee by taking email and password as parameters
	@GetMapping("/findbyName/{name}")
	public EmployeeDto2 getEmployeeByName(@PathVariable String name) {
		return employeeService.findbyName(name);
	}

}
