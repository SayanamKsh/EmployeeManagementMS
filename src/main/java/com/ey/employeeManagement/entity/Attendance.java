package com.ey.employeeManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Attendance {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	 	private Long empId;
	    private String date;
	    private String isPresent;
	    
	    
	    
	    
	    
		public Long getEmpId() {
			return empId;
		}
		public void setEmpId(Long empId) {
			this.empId = empId;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getIsPresent() {
			return isPresent;
		}
		public void setIsPresent(String isPresent) {
			this.isPresent = isPresent;
		}
		@Override
		public String toString() {
			return "Attendance [id=" + id + ", empId=" + empId + ", date=" + date + ", isPresent=" + isPresent + "]";
		}
	    
	    
}
