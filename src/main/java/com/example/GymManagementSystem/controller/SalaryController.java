package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.DTO.SalaryDTO;
import com.example.GymManagementSystem.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @PostMapping("/calculate")
    public ResponseEntity<List<SalaryDTO>> calculateSalaries(@RequestParam int month, @RequestParam int year) {
        List<SalaryDTO> salaries = salaryService.calculateSalaries(month, year);
        return ResponseEntity.ok(salaries);
    }
}