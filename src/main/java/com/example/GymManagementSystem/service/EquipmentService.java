package com.example.GymManagementSystem.service;

import com.example.GymManagementSystem.entity.Equipment;
import com.example.GymManagementSystem.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    // Lấy tất cả thiết bị
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // Lấy thiết bị theo ID
    public Optional<Equipment> getEquipmentById(Integer id) {
        return equipmentRepository.findById(id);
    }

    // Thêm thiết bị mới
    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // Cập nhật thiết bị
    public Equipment updateEquipment(Integer id, Equipment updatedEquipment) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found with ID: " + id));
        equipment.setName(updatedEquipment.getName());
        equipment.setCategory(updatedEquipment.getCategory());
        equipment.setQuantity(updatedEquipment.getQuantity());
        equipment.setPrice(updatedEquipment.getPrice());
        equipment.setStatus(updatedEquipment.getStatus());
        equipment.setDescription(updatedEquipment.getDescription());
        return equipmentRepository.save(equipment);
    }

    // Xóa thiết bị
    public void deleteEquipment(Integer id) {
        equipmentRepository.deleteById(id);
    }
}
