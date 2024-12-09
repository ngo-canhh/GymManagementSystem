package com.example.GymManagementSystem.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GymManagementSystem.entity.Service;
import com.example.GymManagementSystem.repository.ServiceRepository;

import org.springframework.ui.Model;


@Controller
public class PageController {
    @Autowired
    private ServiceRepository serviceRepository;
    
    @GetMapping({"/", ""})
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/service")
    public String service(Model model) {
        model.addAttribute("categories", serviceRepository.findDistinctCategory());
        model.addAttribute("services", serviceRepository.findAllServices());
        return "services";
    }

    @GetMapping("/service_detail")
    public String getMethodName(@RequestParam int id, Model model) {
        model.addAttribute("service", serviceRepository.findServiceByID(id));
        return "service_detail";
    }
    
    @GetMapping("/list_service")
    public ResponseEntity<?> getMethodName(@RequestParam String category) {
        List<Service> services = serviceRepository.findByCategory(category);
        return ResponseEntity.ok(services);
    }   
    
}
