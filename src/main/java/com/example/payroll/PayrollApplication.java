package com.example.payroll;

import com.example.payroll.models.Employee;
import com.example.payroll.models.Order;
import com.example.payroll.models.Status;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PayrollApplication {

    //private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    public static void main(String[] args) {
        SpringApplication.run(PayrollApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository, OrderRepository orderRepository) {
        return args -> {
            repository.save(new Employee("mariam", "Kvantaliani", "Software Engineer"));
            repository.save(new Employee("Anna", "Kvantaliani", "Teacher"));

            orderRepository.save(new Order("iPhone 13 Pro Max", String.valueOf(Status.COMPLETED)));
            orderRepository.save(new Order("iPad 10 Gen", String.valueOf(Status.IN_PROGRESS)));
        };
    }
}
