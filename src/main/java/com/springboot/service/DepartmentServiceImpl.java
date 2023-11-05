package com.springboot.service;

import com.springboot.entity.Department;
import com.springboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepository deptRepo;

    @Override
    public List<Department> getAllDept() {
        return deptRepo.findAll();
    }

    @Override
    public Optional<Department> getDeptById(int id) {
        return deptRepo.findById(id);
    }

    @Override
    public Department addDept(Department department) {
        return deptRepo.save(department);
    }

    @Override
    public String deleteDept(int id) {
           deptRepo.deleteById(id);
           return "";
    }
}
