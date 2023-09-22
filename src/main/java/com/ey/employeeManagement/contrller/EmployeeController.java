package com.ey.employeeManagement.contrller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.employeeManagement.entity.Employee;
import com.ey.employeeManagement.service.EmployeeService;

import jakarta.ws.rs.QueryParam;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/signUp")
	public ResponseEntity<Object> employeeSignUp(@RequestBody Employee employee) {
		try {
			employeeService.employeeSignUp(employee);
			return new ResponseEntity<Object>("Successfully Signed Up", HttpStatus.CREATED);
		}catch(Exception e) {
			if(e.getCause().toString().contains("Duplicate entry")) {
				return new ResponseEntity<Object>(employee.getEmail()+" is already been used", HttpStatus.OK);
			}
			return new ResponseEntity<Object>(e.getCause(), HttpStatus.OK);
		}
	}
	@PostMapping("/logIn")
	public ResponseEntity<String> employeeLogIn(@RequestBody Employee employee) {
		if(employeeService.employeeLogIn(employee)) {
			return new ResponseEntity<String>("Successfully Loged In", HttpStatus.OK);
		}
		return new ResponseEntity<String>("LogIn Failed. Please enter valid UserName or Password", HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable("id") Long id) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeDetails(id), HttpStatus.OK);
	}
	
	@GetMapping("/isEmailExists")
	public ResponseEntity<Boolean> isEmailExists(@QueryParam("email") String email) {
		return new ResponseEntity<Boolean>(employeeService.isEmailExists(email), HttpStatus.OK);
	}
}
