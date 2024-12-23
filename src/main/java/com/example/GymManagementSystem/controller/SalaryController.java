package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.DTO.SalaryDTO;
import com.example.GymManagementSystem.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller 
@RequestMapping("/api/salaries")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

   @GetMapping("/salaryPage")  
    public String showSalaryPage(){
        return "admin_salary_calculation";  
    }

    @PostMapping("/calculate")
    @ResponseBody 
    public ResponseEntity<List<SalaryDTO>> calculateSalaries(@RequestParam int month, @RequestParam int year) {
        List<SalaryDTO> salaries = salaryService.calculateSalaries(month, year);
        return ResponseEntity.ok(salaries);
    }
}