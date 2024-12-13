package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.DTO.SalaryDTO;
import com.example.GymManagementSystem.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/calculate")
    public String calculateSalary(@RequestBody SalaryDTO salaryDTO) {
        salaryService.calculateSalary(salaryDTO);
        return "Tính lương thành công!";
    }
}