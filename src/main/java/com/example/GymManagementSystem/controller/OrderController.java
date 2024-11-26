package com.example.GymManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class OrderController {
    @GetMapping("/order")
    public String shoeOrder(Model model) {
        return "StaffViewsHtml/order";
    }
}
