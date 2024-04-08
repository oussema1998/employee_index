package com.example.employee.controllers;

import com.example.employee.entities.Employee;
import com.example.employee.interfaces.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/getAll")
    public List<Employee> GetAllEmployees(){
        return employeeRepo.findAll();
    }
}
