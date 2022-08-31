package com.example.payroll.exceptions;

import com.example.payroll.controllers.EmployeeController;
import com.example.payroll.models.Employee;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        try {
            return EntityModel.of(employee,
                    linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                    linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollectionModel<EntityModel<Employee>> toCollectionModel(Iterable<? extends Employee> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
