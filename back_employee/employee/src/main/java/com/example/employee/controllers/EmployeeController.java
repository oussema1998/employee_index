package com.example.employee.controllers;

import com.example.employee.entities.Employee;
import com.example.employee.interfaces.EmployeeRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/getAll")
    public List<Employee> GetAllEmployees(){
        return employeeRepo.findAll();
    }

    @PostMapping("/add")
    public Employee AddEmployee(@Valid @RequestBody Employee e){return  employeeRepo.save(e);}

    @GetMapping("/get/{id}")
public Employee getEmployee( @PathVariable Integer id){return employeeRepo.findById(id).orElse(null);}

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeRepo.deleteById(id);
    }


}
