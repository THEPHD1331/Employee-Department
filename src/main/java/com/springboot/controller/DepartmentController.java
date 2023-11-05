package com.springboot.controller;

import com.springboot.entity.Department;
import com.springboot.exception.DeptNotFound;
import com.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    List<Department> getAllDept() throws DeptNotFound {
        if(CollectionUtils.isEmpty(departmentService.getAllDept())){
            throw new DeptNotFound("Departments Not Found!");
        }
        return departmentService.getAllDept();
    }

    @GetMapping("/{id}")
    Optional<Department> getDeptById(@PathVariable int id) throws DeptNotFound {
        if(departmentService.getDeptById(id)==null){
            throw new DeptNotFound("Department Not Found for Id: "+id);
        }

        return departmentService.getDeptById(id);
    }

    @PostMapping
    Department addDepartment(@RequestBody Department department) throws DeptNotFound {
        if(department==null){
            throw new DeptNotFound("ERROR: Not Added - No Body Found!");
        }

        return departmentService.addDept(department);
    }

    @PutMapping
    Department updateDepartment(@RequestBody Department department) throws DeptNotFound{
        if(department==null){
            throw new DeptNotFound("ERROR: Not Updated - No Body Found!");
        }

        return departmentService.addDept(department);
    }

    @DeleteMapping("/{id}")
    String deleteDepartment(@PathVariable int id) throws DeptNotFound {
        if(departmentService.getDeptById(id)==null){
            throw new DeptNotFound("Department Not Found for Id: "+id);
        }
        departmentService.deleteDept(id);
        return "Department Deleted by Id: "+id;
    }
}
