package com.springboot.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "departments")
@Data
public class Department {

    @Id
    private int departmentId;
    private String departmentName;
    private Date createdAt;
    private Date updatedAt;
}
