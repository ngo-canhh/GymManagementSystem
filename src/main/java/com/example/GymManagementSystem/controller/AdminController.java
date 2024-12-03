package com.example.GymManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.GymManagementSystem.entity.Service;
import com.example.GymManagementSystem.service.ServiceService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/service")
    public String showAdminServicePage(Model model){
        model.addAttribute("services", serviceService.getAllServices());
        // return "AdminView/admin_service";
        List<Service> services = serviceService.getAllServices();
        System.out.println(services.size());
        for (Service service : services) {
            System.out.println(service.getID());
        }
        return "AdminView/admin_service";
    }

} 
