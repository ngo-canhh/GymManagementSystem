package com.example.GymManagementSystem.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.Service;
import com.example.GymManagementSystem.repository.CustomerRepository;
import com.example.GymManagementSystem.repository.ServiceRepository;
import com.example.GymManagementSystem.service.CustomerService;

import org.springframework.ui.Model;


@Controller
public class PageController {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    
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

    @GetMapping("/registed_service")
    public String registed_service(Model model) {
        Customer customer = customerRepository.findCustomerByID(1);

        model.addAttribute("courses",customerService.getCustomerServiceByCustomer(customer));
        return "courseInfo";
    }
    
}
