package com.aja.java.employeeMapper;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aja.java.controller.EmployeeController;
import com.aja.java.dto.EmployeeDto;
import com.aja.java.dto.EmployeeDto2;
import com.aja.java.entity.Employee;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class EmployeeMapper {

	@Autowired(required = true)
	AmazonS3 amazonS3;
	
	@Value("${application.bucket.name}")
	String bucketName;
	
 
	private static final Logger logger=Logger.getLogger(EmployeeController.class);
	
	public Employee convertEmployeeTOEmployeeDto(EmployeeDto employeeDto) {
		Employee mappedEMployee = new Employee();
		
		mappedEMployee.setName(employeeDto.getName());
		mappedEMployee.setUserName(employeeDto.getUserName());
		mappedEMployee.setPassword(employeeDto.getPassword());
		mappedEMployee.setDateOfJoining(employeeDto.getDateOfJoining());
		mappedEMployee.setNoOfDaysInCurrentCompany(employeeDto.getNoOfDaysInCurrentCompany());
		mappedEMployee.setDesignation(employeeDto.getDesignation());
		mappedEMployee.setReportingManager(employeeDto.getReportingManager());
		mappedEMployee.setPreviousClient(employeeDto.getPreviousClient());
		mappedEMployee.setNoOfDaysInBench(employeeDto.getNoOfDaysInBench());
		mappedEMployee.setTechnology(employeeDto.getTechnology());
		mappedEMployee.setPhoneNumber(employeeDto.getPhoneNumber());
		mappedEMployee.setEmailId(employeeDto.getEmailId());
		mappedEMployee.setCurrentAddress(employeeDto.getCurrentAddress());
		mappedEMployee.setPermanentAddress(employeeDto.getPermanentAddress());
		mappedEMployee.setLaptopStatus(employeeDto.getLaptopStatus());
		
		return mappedEMployee;

	}
	
	public EmployeeDto2 convertEmployeeDto2TOEmployee(Employee employee) {
		EmployeeDto2 mappedEmployeeDto = new EmployeeDto2();
		
		mappedEmployeeDto.setName(employee.getName());
		mappedEmployeeDto.setUserName(employee.getUserName());
		mappedEmployeeDto.setPassword(employee.getPassword());
		S3Object s3Object =	amazonS3.getObject(bucketName, employee.getEmployeeImage());
		S3ObjectInputStream fileInputStream = s3Object.getObjectContent();
		
		try {
		byte[] bs =	IOUtils.toByteArray(fileInputStream);
		mappedEmployeeDto.setFile(bs);
		} catch (IOException e) {
			logger.error("error in getting the file");
		}

		
		mappedEmployeeDto.setDateOfJoining(employee.getDateOfJoining());
		mappedEmployeeDto.setNoOfDaysInCurrentCompany(employee.getNoOfDaysInCurrentCompany());
		mappedEmployeeDto.setDesignation(employee.getDesignation());
		mappedEmployeeDto.setReportingManager(employee.getReportingManager());
		mappedEmployeeDto.setPreviousClient(employee.getPreviousClient());
		mappedEmployeeDto.setNoOfDaysInBench(employee.getNoOfDaysInBench());
		mappedEmployeeDto.setTechnology(employee.getTechnology());
		mappedEmployeeDto.setPhoneNumber(employee.getPhoneNumber());
		mappedEmployeeDto.setEmailId(employee.getEmailId());
		mappedEmployeeDto.setCurrentAddress(employee.getCurrentAddress());
		mappedEmployeeDto.setPermanentAddress(employee.getPermanentAddress());
		return mappedEmployeeDto;
	}
}
