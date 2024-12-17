package com.example.GymManagementSystem.service;

import com.example.GymManagementSystem.DTO.SalaryDTO;
import com.example.GymManagementSystem.entity.Staff;
import com.example.GymManagementSystem.entity.StaffRole;
import com.example.GymManagementSystem.entity.PositionInformation;
import com.example.GymManagementSystem.repository.StaffRepository;
import com.example.GymManagementSystem.repository.StaffRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class SalaryService {

    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private StaffRoleRepository staffRoleRepository;

    public List<SalaryDTO> calculateSalaries(int month, int year) {
        List<SalaryDTO> salaryDTOs = new ArrayList<>();
         List<Staff> staffs = staffRepository.findAllStaff();
        for (Staff staff : staffs) {
            StaffRole staffRole = staffRoleRepository.findActiveStaffRoleById(staff.getID());

            if (staffRole != null && staffRole.getPositionInformation() != null) {
                PositionInformation positionInformation = staffRole.getPositionInformation();
                double salaryAmount = positionInformation.getBasicSalary();
                salaryDTOs.add(new SalaryDTO(staff.getID(), month, year, salaryAmount));
            }
        }
        return salaryDTOs;
    }

    public List<SalaryDTO> getSalariesByMonthAndYear(int month, int year) {
        List<SalaryDTO> salaryDTOs = new ArrayList<>();
        List<Staff> staffs = staffRepository.findAll();
        
        for (Staff staff : staffs) {
            StaffRole staffRole = staffRoleRepository.findActiveStaffRoleById(staff.getID());
            
            if (staffRole != null && staffRole.getPositionInformation() != null) {
                PositionInformation positionInformation = staffRole.getPositionInformation();
                double salaryAmount = positionInformation.getBasicSalary();
                
                salaryDTOs.add(new SalaryDTO(staff.getID(), month, year, salaryAmount));
            }
        }
        
        return salaryDTOs;
    }
    public SalaryDTO getSalaryByEmployeeIdAndMonthAndYear(Long employeeId, int month, int year) {
        return staffRepository.findById(employeeId)
            .map(staff -> {
                StaffRole staffRole = staffRoleRepository.findActiveStaffRoleById(staff.getID());
                if (staffRole != null && staffRole.getPositionInformation() != null) {
                    double salaryAmount = staffRole.getPositionInformation().getBasicSalary();
                    return new SalaryDTO(staff.getID(), month, year, salaryAmount);
                }
                return null; 
            })
            .orElse(null); 
    }
}
