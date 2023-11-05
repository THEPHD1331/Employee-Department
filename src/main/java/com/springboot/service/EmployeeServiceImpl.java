package com.springboot.service;

import com.springboot.entity.Employee;
import com.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return empRepo.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return empRepo.save(employee);
    }

    @Override
    public String deleteEmployee(int id) {
             empRepo.deleteById(id);
             return "";
    }
}
