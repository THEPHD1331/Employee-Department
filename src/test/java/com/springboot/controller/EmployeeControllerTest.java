package com.springboot.controller;

import com.springboot.entity.Employee;
import com.springboot.exception.EmployeeNotFound;
import com.springboot.service.EmployeeService;
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
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() throws Exception{

        List<Employee> empList= new ArrayList<>();
        Employee emp = new Employee();
       emp.setId(3);
       emp.setFirstName("Harsh");
       emp.setLastName("Parate");
       emp.setEmail("harsh@gmail.com");
       emp.setDepartmentId("3");
       emp.setCreatedAt(new Date());
       emp.setUpdatedAt(new Date());
       empList.add(emp);

        when(employeeService.getAllEmployees()).thenReturn(empList);
        RequestBuilder builder= MockMvcRequestBuilders
                .get("/employees")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void testEmployeeNotFoundExp() throws Exception {

        when(employeeService.getAllEmployees()).thenThrow(new EmployeeNotFound("Not Found"));
        RequestBuilder builder= MockMvcRequestBuilders
                .get("/employees")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isNotFound());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee emp = new Employee();
        emp.setId(3);
        emp.setFirstName("Harsh");
        emp.setLastName("Parate");
        emp.setEmail("harsh@gmail.com");
        emp.setDepartmentId("3");
        emp.setCreatedAt(new Date());
        emp.setUpdatedAt(new Date());

        when(employeeService.getEmployeeById(anyInt())).thenReturn(Optional.of(emp));
        RequestBuilder builder= MockMvcRequestBuilders
                .get("/employees/3")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee emp = new Employee();
        emp.setId(3);
        emp.setFirstName("Harsh");
        emp.setLastName("Parate");
        emp.setEmail("harsh@gmail.com");
        emp.setDepartmentId("3");
        emp.setCreatedAt(new Date());
        emp.setUpdatedAt(new Date());

        when(employeeService.addEmployee(any())).thenReturn(emp);
        RequestBuilder builder= MockMvcRequestBuilders.post("/employees")
                .content(asJsonString(emp))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee emp = new Employee();
        emp.setId(3);
        emp.setFirstName("Harsh");
        emp.setLastName("Parate");
        emp.setEmail("harshparate@gmail.com");
        emp.setDepartmentId("3");
        emp.setCreatedAt(new Date());
        emp.setUpdatedAt(new Date());

        when(employeeService.addEmployee(any())).thenReturn(emp);
        RequestBuilder builder= MockMvcRequestBuilders.put("/employees")
                .content(asJsonString(emp))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        when(employeeService.deleteEmployee(anyInt())).thenReturn("Employee is deleted!");
        RequestBuilder builder= MockMvcRequestBuilders.delete("/employees/4")
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
