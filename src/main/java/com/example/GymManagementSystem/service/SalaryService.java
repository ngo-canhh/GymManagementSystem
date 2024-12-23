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

    @Autowired
    private NotificationService notificationService;

    public List<SalaryDTO> calculateSalaries(int month, int year) {
        List<SalaryDTO> salaryDTOs = new ArrayList<>();
        List<Staff> staffs = staffRepository.findAllStaff();

        for (Staff staff : staffs) {
            StaffRole staffRole = staffRoleRepository.findActiveStaffRoleById(staff.getID());

            if (staffRole != null && staffRole.getPositionInformation() != null) {
                PositionInformation positionInformation = staffRole.getPositionInformation();
                double salaryAmount = positionInformation.getBasic_salary();

                salaryDTOs.add(new SalaryDTO(staff.getID(), month, year, salaryAmount));
                
                String message = "Lương của bạn cho tháng " + month + "/" + year + " là: " + salaryAmount;
                notificationService.sendNotification(staff.getID(), message);
            }
        }
        return salaryDTOs;
    }
}
