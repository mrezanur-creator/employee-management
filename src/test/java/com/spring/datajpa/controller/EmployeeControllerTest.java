package com.spring.datajpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.datajpa.model.Employee;
import com.spring.datajpa.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. ADD EMPLOYEE
    @Test
    void addEmployee() throws Exception {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setEmployeeName("Reza");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(emp);

        mockMvc.perform(post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Reza"));
    }

    // 2. GET ALL
    @Test
    void getAllEmployees() throws Exception {
        when(employeeService.getAllEmployees())
                .thenReturn(List.of(new Employee()));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk());
    }

    // 3. GET BY ID
    @Test
    void getEmployeeById() throws Exception {
        Employee emp = new Employee();
        emp.setId(1);

        when(employeeService.getEmployeeById(1))
                .thenReturn(Optional.of(emp));

        mockMvc.perform(get("/api/employee/1"))
                .andExpect(status().isOk());
    }

    // 4. UPDATE
    @Test
    void updateEmployee() throws Exception {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setEmployeeName("Updated");

        when(employeeService.updateEmployee(any(Employee.class)))
                .thenReturn(emp);

        mockMvc.perform(put("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Updated"));
    }

    // 5. DELETE
    @Test
    void deleteEmployee() throws Exception {

        doNothing().when(employeeService).deleteEmployee(1);

        mockMvc.perform(delete("/api/employee/1"))
                .andExpect(status().isOk());
    }
}