package com.example.GymManagementSystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.entity.StaffRole;
import com.example.GymManagementSystem.repository.StaffRoleRepository;

@Service
public class StaffRoleService {
    @Autowired
    private StaffRoleRepository staffRoleRepository;

    public Map<String, Object> getAllStaff() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", staffRoleRepository.findAllStaffRoles());
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> getStaffByID(int staffID) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", staffRoleRepository.findInforStaff(staffID));
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    public StaffRole getActiveStaffRoleByID(int staffID){
        return staffRoleRepository.findActiveStaffRoleById(staffID);
    }
}
