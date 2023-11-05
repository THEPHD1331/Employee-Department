package com.springboot.controller;

import com.springboot.entity.Employee;
import com.springboot.exception.EmployeeNotFound;
import com.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    List<Employee> getAllEmployees() throws EmployeeNotFound {
        if(CollectionUtils.isEmpty( employeeService.getAllEmployees())){
            throw new EmployeeNotFound("Employees Not Found!");
        }

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    Optional<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFound {

        Optional<Employee> emp=  employeeService.getEmployeeById(id);
        if(emp==null){
            throw new EmployeeNotFound("Employee Not Found for id: "+id);
        }
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    Employee addEmployee(@RequestBody Employee employee) throws EmployeeNotFound {

        if(employee==null){
            throw new EmployeeNotFound("ERROR: Employee Not Added - No Args Passed!");
        }
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    Employee updateEmployee(@RequestBody Employee employee) throws EmployeeNotFound {

        if(employee==null){
            throw new EmployeeNotFound("ERROR: Employee Not Updated - No Changes Detected!");
        }
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    String deleteEmployee(@PathVariable int id) throws EmployeeNotFound {

        Optional<Employee> emp=  employeeService.getEmployeeById(id);
        if(emp==null){
            throw new EmployeeNotFound("ERROR: Employee Not Deleted - Employee Not Found for Id: "+id);
        }
        employeeService.deleteEmployee(id);
        return "Employee Deleted for Id: "+id;
    }

}
