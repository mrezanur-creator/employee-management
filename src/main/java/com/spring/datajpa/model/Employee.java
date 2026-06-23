package com.spring.datajpa.model;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	
	@Id
	@NotNull
	private Integer id;
	

	@Column(name = "employee_name")
	@NotBlank
	private String employeeName;
	

	@Column(name = "designation")
	@NotBlank
	private String designation;
	

	@Column(name = "experience")
	@NotNull
	private Double experience;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String employeeName, String designation, double experience) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.designation = designation;
		this.experience = experience;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", designation=" + designation
				+ ", experience=" + experience + "]";
	}
	
}
