package com.springboot.service;

import com.springboot.entity.Department;
import com.springboot.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Test
    public void testGetAllDept() throws Exception{

        List<Department> deptList= new ArrayList<>();
        Department dept = new Department();
        dept.setDepartmentId(5);
        dept.setDepartmentName("Engine");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());
        deptList.add(dept);

        when(departmentRepository.findAll()).thenReturn(deptList);
        List<Department> department= departmentService.getAllDept();
        assertEquals(1, department.size());
    }

    @Test
    public void testGetDeptById() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(5);
        dept.setDepartmentName("Engine");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());

        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(dept));
       Optional<Department> department= departmentService.getDeptById(5);
       assertEquals(dept.getDepartmentName(), department.get().getDepartmentName());
    }

    @Test
    public void testSaveDept() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(4);
        dept.setDepartmentName("Research");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());

        when(departmentRepository.save(any())).thenReturn(dept);
        Department department= departmentService.addDept(dept);
        assertEquals("Research", department.getDepartmentName());

    }

    @Test
    public void testUpdateDept() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(4);
        dept.setDepartmentName("Forensic");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());

        when(departmentRepository.save(any())).thenReturn(dept);
        Department department= departmentService.addDept(dept);
        assertEquals("Forensic", department.getDepartmentName());
    }

    @Test
    public void deleteDeptTest(){
        Department dept = new Department();
        dept.setDepartmentId(5);
        dept.setDepartmentName("Build");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());
        departmentRepository.deleteById(anyInt());
        departmentService.deleteDept(5);
    }
}
