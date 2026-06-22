package com.spring.datajpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.datajpa.model.Employee;
import com.spring.datajpa.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.doThrow;

import static org.mockito.Mockito.verify;

/*Gunakan Mockito saat menjalankan test ini*/
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	/* untuk melakukan test namun tidak dengan db aslipakai mock ini */
    @Mock
    private EmployeeRepository employeeRepository;

	/* untuk menyuntikan mock repository db tadi ke service */
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetEmployeeById() {

        Employee employee =
                new Employee(1, "Reza", "Developer", 5.0);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));

        Optional<Employee> result =
                employeeService.getEmployeeById(1);

        assertEquals("Reza",
                result.get().getEmployeeName());
    }
    
    @Test
    void testGetEmployeeById_NotFound() {

        when(employeeRepository.findById(99))
                .thenReturn(Optional.empty());

        Optional<Employee> result =
                employeeService.getEmployeeById(99);

        assertEquals(Optional.empty(), result);
    }
    
    @Test
    void testAddEmployee() {

        Employee employee =
                new Employee(1, "Reza", "Developer", 5.0);

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee result =
                employeeService.addEmployee(employee);

        assertEquals("Reza",
                result.getEmployeeName());
    }
    
    @Test
    void testAddEmployee_Null() {

        when(employeeRepository.save(null))
                .thenThrow(new IllegalArgumentException("Employee cannot be null"));

        try {
            employeeService.addEmployee(null);
        } catch (Exception e) {
            assertEquals("Employee cannot be null", e.getMessage());
        }
    }
    
    @Test
    void testGetAllEmployees() {

        List<Employee> employees =
                Arrays.asList(
                        new Employee(1, "Reza", "Developer", 5.0),
                        new Employee(2, "Budi", "Tester", 3.0));

        when(employeeRepository.findAll())
                .thenReturn(employees);

        List<Employee> result =
                employeeService.getAllEmployees();

        assertEquals(2, result.size());
    }
    
    @Test
    void testGetAllEmployees_Empty() {

        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList());

        List<Employee> result =
                employeeService.getAllEmployees();

        assertEquals(0, result.size());
    }
    
    @Test
    void testUpdateEmployee() {

        Employee existingEmployee =
                new Employee(1, "Reza", "Developer", 5.0);

        Employee inputEmployee =
                new Employee(1, "Reza Updated", "Senior Developer", 7.0);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(existingEmployee));

        when(employeeRepository.save(existingEmployee))
                .thenReturn(existingEmployee);

        Employee result =
                employeeService.updateEmployee(inputEmployee);

        assertEquals("Reza Updated",
                result.getEmployeeName());
    }
    
    @Test
    void testUpdateEmployee_NotFound() {

        Employee inputEmployee =
                new Employee(1, "Reza Updated", "Senior Developer", 7.0);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        try {
            employeeService.updateEmployee(inputEmployee);
        } catch (Exception e) {
            assertEquals("Employee not found", e.getMessage());
        }
    }
    
    @Test
    void testDeleteEmployee() {

        employeeService.deleteEmployee(1);
		/* verify hanya untuk memastikan apakah deletebyId bnr2 di panggil di service */
        verify(employeeRepository)
                .deleteById(1);
    }
    
    @Test
    void testDeleteEmployee_NotFound() {

        doThrow(new RuntimeException("Employee not found"))
                .when(employeeRepository)
                .deleteById(99);

        try {
            employeeService.deleteEmployee(99);
        } catch (Exception e) {
            assertEquals("Employee not found", e.getMessage());
        }
    }
}