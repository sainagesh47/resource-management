package com.aja.java.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aja.java.controller.EmployeeController;
import com.aja.java.dto.EmployeeDto;
import com.aja.java.dto.EmployeeDto2;
import com.aja.java.employeeMapper.EmployeeMapper;
import com.aja.java.entity.Employee;
import com.aja.java.repository.EmployeeRepository;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;


@Service
public class EmployeeService {

	private static final Logger logger=Logger.getLogger(EmployeeController.class);
	
	@Autowired(required = true)
	AmazonS3 amazonS3;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${application.bucket.name}")
	String bucketName;
	
	@Autowired
	EmployeeMapper employeeMapper;

//Saving Employee with Employee object and Image
	public String save(EmployeeDto employeeDto,MultipartFile imageFile) {
		String encryptedPassword = passwordEncoder.encode(employeeDto.getPassword());
		employeeDto.setPassword(encryptedPassword);
		File	convertedFile=convertMultiPartFileToFile(imageFile);
		Employee employee = new Employee();
		try {
		  PutObjectResult objectResult =  amazonS3.putObject(bucketName,imageFile.getOriginalFilename(), convertedFile);
		 
			employee = employeeMapper.convertEmployeeTOEmployeeDto(employeeDto);
			employee.setEmployeeImage(imageFile.getOriginalFilename());
		    
		} catch (AmazonServiceException e) {
		    // Handle Amazon service exception (e.g., incorrect credentials, bucket not found, etc.)
		    e.printStackTrace();
		}
		Employee savedEmployee = employeeRepository.save(employee);
		if(savedEmployee != null) {
			return "saved successfully";
		}
		else {
			return "employee not registered";
		}
	}
	
//  converting the image into the file 	
public File convertMultiPartFileToFile(MultipartFile file) {
		
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)){
			fileOutputStream.write(file.getBytes());
		}
		catch (Exception e) {
			logger.error("error occured while converting the file");
		}
		return convertedFile;
	}



//getting all the Employees
	public List<EmployeeDto2> getAll() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDto2> employeeDto2 = new ArrayList<>();
		
	
		employees.stream().forEach(f-> {
			employeeDto2.add(employeeMapper.convertEmployeeDto2TOEmployee(f));
		amazonS3.getObject(bucketName, f.getEmployeeImage());
		});
		return employeeDto2;
	}

	
//  getting Employee by name 
	public EmployeeDto2 findbyName(String name) {

		Employee employee = employeeRepository.findbyName(name);
		
		return employeeMapper.convertEmployeeDto2TOEmployee(employee);
	}

	
//  Logging Employee by taking email and password as parameters
	public String login(String email, String password) {

		Employee employee = employeeRepository.findByEmailId(email);
		if (passwordEncoder.matches(password, employee.getPassword()) == true) {
			return "Login Successful";
		}
		return "Login Unsuccessful";

	}

}
