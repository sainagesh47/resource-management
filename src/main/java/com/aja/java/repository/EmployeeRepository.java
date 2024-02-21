package com.aja.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aja.java.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "select * from `nagesh`.aja_employee where name=:name", nativeQuery = true)
	public Employee findbyName(String name);

	public Employee findByEmailId(String email);

	
}
