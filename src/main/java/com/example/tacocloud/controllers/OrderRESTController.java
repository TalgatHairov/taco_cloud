package com.example.tacocloud.controllers;

import com.example.tacocloud.models.Order;
import com.example.tacocloud.repositories.JpaOrderRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders", consumes = "application/json")
@CrossOrigin(origins = "*")
public class OrderRESTController {

    private JpaOrderRepository orderRepository;

    public OrderRESTController(JpaOrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping(produces = "application/json")
    public Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }
}
