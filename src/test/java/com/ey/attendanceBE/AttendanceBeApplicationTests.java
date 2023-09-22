package com.ey.attendanceBE;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.ey.employeeManagement.Repo.EmployeeRepo;
import com.ey.employeeManagement.config.Config;
import com.ey.employeeManagement.contrller.EmployeeController;
import com.ey.employeeManagement.entity.Employee;
import com.ey.employeeManagement.service.EmployeeService;

@SpringBootTest
@ContextConfiguration(classes = Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttendanceBeApplicationTests {

    
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepo employeeRepo;
    
	Employee defaultTestEmpObj= new Employee("Sayanam","Das","sayanam@gmail.com","pass");
	Long empId;
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Order(1)
	public void employeeSignUpTest(){
		Employee testEmpObj= new Employee("Kshirabdhi","Das","kshirabdhigmail.com","pass");
		if(employeeController.isEmailExists(testEmpObj.getEmail()).getBody()) {
			ResponseEntity<Object> response=employeeController.employeeSignUp(testEmpObj);
			assertEquals(testEmpObj.getEmail()+" is already been used", response.getBody());
			assertEquals(200, response.getStatusCodeValue());
		}else {
			ResponseEntity<Object> response=employeeController.employeeSignUp(testEmpObj);
			assertEquals("Successfully Signed Up", response.getBody());
			assertEquals(201, response.getStatusCodeValue());
		}
	}
	
	@Test
	@Order(2)
	public void employeeLogIn(){
		Employee testEmpObj= new Employee("","","kshirabdhi@gmail.com","pass");
		ResponseEntity<String> response=employeeController.employeeLogIn(testEmpObj);
		if(200==response.getStatusCodeValue()) {
			assertEquals(testEmpObj.getEmail()+"Successfully Loged In", response.getBody());
		}else if(401 ==response.getStatusCodeValue()) {
			assertEquals("LogIn Failed. Please enter valid UserName or Password", response.getBody());
		}
		
	}
	
	
	
//	@Test
//	@Order(2)
//	public void getAllEmployeesTest(){
//		ResponseEntity<List<Employee>> response=employeeController.getAllEmployees();
//		List<Employee> emp=response.getBody().stream().filter(e -> defaultTestEmpObj.getEmail().equalsIgnoreCase(e.getEmail())).collect(Collectors.toList());
//		assertNotNull(emp);
//		if(!emp.isEmpty()) {
//			defaultTestEmpObj.setId(emp.get(0).getId());
//			assertEquals(defaultTestEmpObj.getEmail(), emp.get(0).getEmail());
//			assertEquals(defaultTestEmpObj.getFirstName(), emp.get(0).getFirstName());
//			assertEquals(defaultTestEmpObj.getLastName(), emp.get(0).getLastName());
//			assertEquals(defaultTestEmpObj.getPassword(), emp.get(0).getPassword());
//		}
//	}
//	
//	@Test
//	@Order(3)
//	public void getEmployeeDetailsTest(){
//		ResponseEntity<Employee> response=employeeController.getEmployeeDetails(defaultTestEmpObj.getId());
//		assertNotNull(response.getBody());
//		if(null !=response.getBody()) {
//			Employee empdetails=response.getBody();
//			assertEquals(defaultTestEmpObj.getId(), empdetails.getId());
//			assertEquals(defaultTestEmpObj.getEmail(), empdetails.getEmail());
//			assertEquals(defaultTestEmpObj.getFirstName(), empdetails.getFirstName());
//			assertEquals(defaultTestEmpObj.getLastName(), empdetails.getLastName());
//			assertEquals(defaultTestEmpObj.getPassword(), empdetails.getPassword());
//		}
//	}


}
