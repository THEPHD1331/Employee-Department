package com.springboot.exception;

public class EmployeeNotFound extends Exception{

    public EmployeeNotFound() {
        super();
    }

    public EmployeeNotFound(String message) {
        super(message);
    }

    public EmployeeNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
