package com.springboot.service;

import com.springboot.entity.Employee;
import com.springboot.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    public void getAllEmployeesTest(){

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

        when(employeeRepository.findAll()).thenReturn(empList);
        List<Employee> employees= employeeService.getAllEmployees();
        assertEquals(1, employees.size());
    }

    @Test
    public void getEmployeeByIdTest(){
        Employee emp = new Employee();
        emp.setId(3);
        emp.setFirstName("Harsh");
        emp.setLastName("Parate");
        emp.setEmail("harsh@gmail.com");
        emp.setDepartmentId("3");
        emp.setCreatedAt(new Date());
        emp.setUpdatedAt(new Date());


        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(emp));
         Optional<Employee> employee= employeeService.getEmployeeById(3);
         assertEquals(emp.getFirstName(),employee.get().getFirstName());
    }

    @Test
    public void saveEmployeeTest(){
        Employee emp = new Employee();
        emp.setId(3);
        emp.setFirstName("Harsh");
        emp.setLastName("Parate");
        emp.setEmail("harsh@gmail.com");
        emp.setDepartmentId("3");
        emp.setCreatedAt(new Date());
        emp.setUpdatedAt(new Date());

        when(employeeRepository.save(any())).thenReturn(emp);
        Employee employee= employeeService.addEmployee(emp);
        assertEquals("Harsh", employee.getFirstName());
    }

    @Test
    public void updateEmployeeTest(){
        Employee emp = new Employee();
        emp.setId(3);
        emp.setFirstName("Raj");
        emp.setLastName("Parate");
        emp.setEmail("harshme@gmail.com");
        emp.setDepartmentId("3");
        emp.setCreatedAt(new Date());
        emp.setUpdatedAt(new Date());

        when(employeeRepository.save(any())).thenReturn(emp);
        Employee employee= employeeService.addEmployee(emp);
        assertEquals("Raj", employee.getFirstName());
    }

    @Test
    public void deleteEmployeeTest(){
        Employee emp = new Employee();
        emp.setId(4);
        emp.setFirstName("Raj");
        emp.setLastName("Gahume");
        emp.setEmail("Raj@gmail.com");
        emp.setDepartmentId("4");
        emp.setCreatedAt(new Date());
        emp.setUpdatedAt(new Date());

        employeeRepository.deleteById(anyInt());
        employeeService.deleteEmployee(4);
    }

}
