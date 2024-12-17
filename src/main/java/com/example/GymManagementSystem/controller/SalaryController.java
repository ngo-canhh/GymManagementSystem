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

    @GetMapping("/list")
    public ResponseEntity<List<SalaryDTO>> getSalariesByMonthAndYear(@RequestParam int month, @RequestParam int year) {
        List<SalaryDTO> salaries = salaryService.getSalariesByMonthAndYear(month, year);
        return ResponseEntity.ok(salaries);
    }
   @GetMapping("/employee/{employeeId}")
       public ResponseEntity<SalaryDTO> getSalaryByEmployeeIdAndMonthAndYear(
            @PathVariable Long employeeId,
            @RequestParam int month,
            @RequestParam int year
        ) {
             SalaryDTO salary =  salaryService.getSalaryByEmployeeIdAndMonthAndYear(employeeId, month, year);
             if (salary == null) {
                return ResponseEntity.notFound().build(); 
            }
             return ResponseEntity.ok(salary);
        }
}