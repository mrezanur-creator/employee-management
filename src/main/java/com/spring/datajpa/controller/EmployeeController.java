package com.spring.datajpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.datajpa.model.Employee;
import com.spring.datajpa.repository.EmployeeRepository;
import com.spring.datajpa.service.*;
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployes(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable(value = "id") Integer id){
		return employeeService.getEmployeeById(id);
	}
	
	@PutMapping("/employee")
	public Employee updateEmployeeById(@RequestBody Employee inputEmployee){
	    return employeeService.updateEmployee(inputEmployee);
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeById(@PathVariable(value = "id") Integer id){
		employeeService.deleteEmployee(id);
	}
	
	
}
