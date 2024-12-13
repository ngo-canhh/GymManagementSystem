package com.example.GymManagementSystem.service;

import com.example.GymManagementSystem.DTO.SalaryDTO;
import com.example.GymManagementSystem.entity.Staff;
import com.example.GymManagementSystem.repository.StaffRepository;
import com.example.GymManagementSystem.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private StaffRepository StaffRepository;
    
    @Autowired
    private NotificationRepository notificationRepository;

    public void calculateSalary(SalaryDTO salaryDTO) {
        List<Staff> Staffs = StaffRepository.findAll(); 
        for (Staff Staff : Staffs) {
            double salary = calculateStaffSalary(Staff, salaryDTO.getYear(), salaryDTO.getMonth());

            // Staff.setSalary(salary);
            StaffRepository.save(Staff);

            // sendSalaryNotification(Staff, salaryDTO);
        }
    }

    private double calculateStaffSalary(Staff Staff, int year, int month) {
        return 1000.0; 
    }

    // private void sendSalaryNotification(Staff Staff, SalaryDTO salaryDTO) {
    //     String notificationContent = "Lương tháng " + salaryDTO.getMonth() + "/" + salaryDTO.getYear() + " đã được tính. Vui lòng kiểm tra!";
    //     notificationRepository.save(new Notification(Staff.getId(), notificationContent));
    // }
}
