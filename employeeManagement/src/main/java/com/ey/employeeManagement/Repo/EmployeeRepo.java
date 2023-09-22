package com.ey.employeeManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.employeeManagement.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	 Employee findByEmail(String email);



}
