package com.springboot.service;

import com.springboot.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> getAllDept();
    Optional<Department> getDeptById(int id);
    Department addDept(Department department);
    String deleteDept(int id);
}
