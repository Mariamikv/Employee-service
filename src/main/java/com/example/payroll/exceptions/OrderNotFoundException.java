package com.example.payroll.exceptions;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(Long id) {
        super("Order not found: " + id);
    }
}
