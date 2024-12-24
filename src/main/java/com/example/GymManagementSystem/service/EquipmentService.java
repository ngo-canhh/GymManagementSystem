package com.example.GymManagementSystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.entity.Equipment;
import com.example.GymManagementSystem.repository.EquipmentRepository;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    public Map<String, Object> getAllEquipment() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", equipmentRepository.findAllEquipment());
            response.put("success", true);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success", false);
        }
        return response;
    }

    public Map<String, Object> addNewEquipment(Equipment equipment){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", equipmentRepository.save(equipment));
            response.put("success", true);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success", false);
        }
        return response;
    }

    public Map<String, Object> updateEquipment(Equipment equipment){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", equipmentRepository.save(equipment));
            response.put("success", true);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success", false);
        }
        return response;
    }

    public Map<String, Object> filterEquipment(String brand, String status, String position){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", equipmentRepository.findEquipmentByBrandStatusPosition(brand, status, position));
            response.put("success", true);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success", false);
        }
        return response;
    }

    public Map<String, Object> searchEquipment(String name){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", equipmentRepository.findEquipmentByName(name));
            response.put("success", true);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success", false);
        }
        return response;
    }

}
