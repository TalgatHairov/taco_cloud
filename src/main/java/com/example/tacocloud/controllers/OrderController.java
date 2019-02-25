package com.example.tacocloud.controllers;

import com.example.tacocloud.models.Order;
import com.example.tacocloud.models.User;
import com.example.tacocloud.repositories.JdbcOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private JdbcOrderRepository orderRepository;

    public OrderController(JdbcOrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orders";
    }

    @PostMapping
    public String proccessOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if(errors.hasErrors())
            return "orders";

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
