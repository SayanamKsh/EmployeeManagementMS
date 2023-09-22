package com.ey.employeeManagement.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.employeeManagement.Repo.EmployeeRepo;
import com.ey.employeeManagement.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	public Employee employeeSignUp(Employee employee) throws SQLException{
		return employeeRepo.save(employee);
	}

	public Boolean employeeLogIn(Employee employee) {
		Employee empObj=employeeRepo.findByEmail(employee.getEmail());
		if(null!=empObj && employee.getPassword().equals(empObj.getPassword()) ) {
			return true;
		}
		return false;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	public Employee getEmployeeDetails(Long id) {
		return employeeRepo.findById(id).get();
	}

	public Boolean isEmailExists(String email) {
		if(null != employeeRepo.findByEmail(email)) {
			if(email.equals(employeeRepo.findByEmail(email).getEmail()))
				return true;
			else
				return false;
		}
		return false;
	}

}
