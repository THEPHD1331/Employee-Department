package com.springboot.service;

import com.springboot.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(int id);
    Employee addEmployee(Employee employee);
    String deleteEmployee(int id);
}
