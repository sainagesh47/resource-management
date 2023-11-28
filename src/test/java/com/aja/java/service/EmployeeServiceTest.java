package com.aja.java.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.aja.java.entity.Employee;
import com.aja.java.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeService employeeService;

//	@Test
//	public void test_saveEmployee() {
//
//		Employee employee = new Employee(4, "lavanya", "lavanya1", "lavanya12", "12-sep-2022", 44, "java developer",
//				"kalyan", "oja", 45, "java", 849348, "lavanya@123", "gachibowli", "elr", "no");
//
//		when(employeeRepository.save(employee)).thenReturn(employee);
//		assertEquals(employee, employeeService.save(employee));
//
//	}

	@Test
	public void test_getAllemployee() {

		List<Employee> list = new ArrayList<>();

		Employee employee = new Employee(1, "lavanya", "lavanya1", "lavanya12", "12-sep-2022", 44, "java developer",
				"kalyan", "oja", 45, "java", 849348, "lavanya@123", "gachibowli", "elr", "no");

		Employee employee1 = new Employee(2, "lavanya", "lavanya1", "lavanya12", "12-sep-2022", 44, "java developer",
				"kalyan", "oja", 45, "java", 849348, "lavanya@123", "gachibowli", "elr", "no");

		list.add(employee);
		list.add(employee1);

		when(employeeRepository.findAll()).thenReturn(list);
		assertEquals(2, employeeService.getAll().size());
	}
	@Test
	public void test_getbyname() {
		
		List<Employee> list = new ArrayList<>();

		Employee employee = new Employee(1, "lavanya", "lavanya", "lavanya12", "12-sep-2022", 44, "java developer",
				"kalyan", "oja", 45, "java", 849348, "lavanya@123", "gachibowli", "elr", "no");
		list.add(employee);
		
		String employeename="lavanya";
		
		when(employeeRepository.findbyName(employeename)).thenReturn(employee);
		assertEquals(employeename, employeeService.findbyName(employeename).getUserName());
		
		
		
	}

}
