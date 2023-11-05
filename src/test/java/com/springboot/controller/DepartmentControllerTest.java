package com.springboot.controller;

import com.springboot.entity.Department;
import com.springboot.exception.DeptNotFound;
import com.springboot.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService departmentService;

    @Test
    public void testGetAllDept() throws Exception{

        List<Department> deptList= new ArrayList<>();
        Department dept = new Department();
        dept.setDepartmentId(5);
        dept.setDepartmentName("Engine");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());
        deptList.add(dept);

        when(departmentService.getAllDept()).thenReturn(deptList);
        RequestBuilder builder= MockMvcRequestBuilders
                .get("/departments")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void testDeptNotFoundExp() throws Exception {

        when(departmentService.getAllDept()).thenThrow(new DeptNotFound("Not Found"));
        RequestBuilder builder= MockMvcRequestBuilders
                .get("/departments")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetDeptById() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(5);
        dept.setDepartmentName("Engine");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());

        when(departmentService.getDeptById(anyInt())).thenReturn(Optional.of(dept));
        RequestBuilder builder= MockMvcRequestBuilders
                .get("/departments/5")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void testSaveDept() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(4);
        dept.setDepartmentName("Research");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());

        when(departmentService.addDept(any())).thenReturn(dept);
        RequestBuilder builder= MockMvcRequestBuilders.post("/departments")
                .content(asJsonString(dept))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void testUpdateDept() throws Exception {
        Department dept = new Department();
        dept.setDepartmentId(4);
        dept.setDepartmentName("Research");
        dept.setCreatedAt(new Date());
        dept.setUpdatedAt(new Date());

        when(departmentService.addDept(any())).thenReturn(dept);
        RequestBuilder builder= MockMvcRequestBuilders.put("/departments")
                .content(asJsonString(dept))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

     @Test
     public void testDeleteDept() throws Exception {
        when(departmentService.deleteDept(anyInt())).thenReturn("Department is deleted!");
         RequestBuilder builder= MockMvcRequestBuilders.delete("/departments/4")
                 .contentType(MediaType.APPLICATION_JSON);

         mockMvc.perform(builder).andExpect(status().isOk());
     }

    public String asJsonString(final Object obj){
        try{
            final ObjectMapper mapper= new ObjectMapper();
            final String jsonContent= mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
