package com.example.payroll.exceptions;

import com.example.payroll.controllers.OrderController;
import com.example.payroll.models.Order;
import com.example.payroll.models.Status;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<Order> toModel(Order order) {
        EntityModel<Order> orderModel = null;
        try {
            orderModel = EntityModel.of(order,
                    linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                    linkTo(methodOn(OrderController.class).all()).withRel("orders"));
        } catch (OrderNotFoundException e) {
            throw new RuntimeException(e);
        }

        // These links are ONLY shown when the order’s status is Status.IN_PROGRESS
        if ((order.getStatus().equals(String.valueOf(Status.IN_PROGRESS)))) {
            try {
                orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
                orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return orderModel;
    }

    @Override
    public CollectionModel<EntityModel<Order>> toCollectionModel(Iterable<? extends Order> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
