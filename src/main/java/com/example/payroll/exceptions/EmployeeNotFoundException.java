package com.example.payroll.exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(Long id){
        super("Could not find employee " + id);
    }
}
