package com.example.GymManagementSystem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.GymManagementSystem.entity.Staff;
import com.example.GymManagementSystem.service.StaffService;

@Controller
@RequestMapping("/admin/staff")
public class AdminStaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("")
    public String showAdminStaffPage(Model model) {
        Map<String, Object> respone = staffService.getAllStaff();
        if ((boolean) respone.get("success")) {
            @SuppressWarnings("unchecked")
            List<Staff> staffs = (List<Staff>) respone.get("data");
            model.addAttribute("staffs", staffs);
        }
        return "AdminView/admin_staff";
    }

    @GetMapping("/staffID")
    public ResponseEntity<?> getInforStaff(@RequestParam int staffID) {
        Map<String, Object> response = staffService.getStaffByID(staffID);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
        }
    }

    @PostMapping("/add_new_staff")
    public ResponseEntity<?> addNewStaff(
            @RequestParam("full_name") String fullName,
            @RequestParam("noID") String noID,
            @RequestParam("age") int age,
            @RequestParam("sex") String sex,
            @RequestParam("date_of_birth") String dateOfBirth,
            @RequestParam("phonenumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("bank_account") String bankAccount,
            @RequestParam("role") String role,
            @RequestParam("address") String address,
            @RequestParam("image") MultipartFile image) {

        Staff staff = new Staff();
        staff.setFull_name(fullName);
        staff.setNoID(noID);
        staff.setAge(age);
        staff.setSex(sex);
        staff.setDate_of_birth(LocalDate.parse(dateOfBirth));
        staff.setPhonenumber(phoneNumber);
        staff.setEmail(email);
        staff.setBank_account(bankAccount);
        staff.setRole(role);
        staff.setAddress(address);

        Map<String, Object> response = staffService.addNewStaff(staff, image);
        if ((boolean) response.get("success")) {
            Staff savedStaff = (Staff) response.get("data");
            return ResponseEntity.ok(savedStaff);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
        }
    }

    @PutMapping("/update_staff")
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff){
        staff = (Staff) staffService.getStaffByID(staff.getID()).get("data");
        Map<String, Object> response = staffService.updateStaff(staff);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
        }
    }

    @GetMapping("/filter_staff")
    public ResponseEntity<?> filterStaff(@RequestParam String filterSex, @RequestParam String filterRole, @RequestParam String filteStatus){
        Map<String, Object> response = staffService.getStaffBySexAndRoleAndStatus(filterSex, filterRole, filteStatus);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
        }
    }
}
