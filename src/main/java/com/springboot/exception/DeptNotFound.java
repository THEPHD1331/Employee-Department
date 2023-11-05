package com.springboot.exception;

public class DeptNotFound extends Exception{

    public DeptNotFound() {
        super();
    }

    public DeptNotFound(String message) {
        super(message);
    }

    public DeptNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
