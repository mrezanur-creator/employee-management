package com.spring.datajpa.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.datajpa.model.Employee;
import com.spring.datajpa.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

	/* Jadi inputEmployee itu pakai data baru */
    public Employee updateEmployee(Employee inputEmployee) {
    	
		
		/*
		 * Nah saat yg findbyId pakai data lama yang sudah di mock saat setup
		 * when(employeeRepository.findById(1))
		 * .thenReturn(Optional.of(existingEmployee)); digunain untuk validasi apakah
		 * datanya sebelumnya memang ada macem validasi select * dulu baru kalo ada
		 * lanjut update date
		 */
		 
    	Employee employee =
    	        employeeRepository.findById(inputEmployee.getId())
    	        .orElseThrow(() -> new RuntimeException("Employee not found"));

		/* bagian ini proses ganti data */
		/* Employee updatedEmployee = employee.get(); */

        employee.setEmployeeName(inputEmployee.getEmployeeName());
        employee.setDesignation(inputEmployee.getDesignation());
        employee.setExperience(inputEmployee.getExperience());

		/*
		 * Nah prosess save ini yang agak tricky karena sebenernya 
		 *  Mockito harus meniru object yang benar-benar dikirim oleh service
		 * bisa gagal match kalau object-nya beda instance.
		 */
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}