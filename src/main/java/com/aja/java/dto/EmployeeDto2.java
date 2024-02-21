package com.aja.java.dto;
 
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto2 {
 
	private String name;
	private String userName;
	private String password;
	private byte[] file;
	private String dateOfJoining;
	private Integer noOfDaysInCurrentCompany;
	private String designation;
	private String reportingManager;
	private String previousClient;
	private Integer noOfDaysInBench;
	private String technology;
	private Long phoneNumber;
	private String emailId;
	private String currentAddress;
	private String permanentAddress;
	private String laptopStatus;
	
}
