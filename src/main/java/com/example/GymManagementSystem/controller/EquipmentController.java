package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.entity.Equipment;
import com.example.GymManagementSystem.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    // Lấy tất cả thiết bị (ai cũng có quyền)
    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    // Lấy thiết bị theo ID (ai cũng có quyền)
    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Integer id) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
        return equipment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Thêm thiết bị mới (chỉ Staff có quyền)
    @PostMapping
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
        if (isStaff()) {
            return ResponseEntity.ok(equipmentService.addEquipment(equipment));
        } else {
            return ResponseEntity.status(403).build();  // Forbidden
        }
    }

    // Cập nhật thiết bị (chỉ Staff có quyền)
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Integer id, @RequestBody Equipment updatedEquipment) {
        if (isStaff()) {
            try {
                Equipment equipment = equipmentService.updateEquipment(id, updatedEquipment);
                return ResponseEntity.ok(equipment);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(403).build();  // Forbidden
        }
    }

    // Xóa thiết bị (chỉ Staff có quyền)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer id) {
        if (isStaff()) {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();  // Forbidden
        }
    }

    // Kiểm tra xem người dùng hiện tại có phải là staff không
    private boolean isStaff() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String role = authentication.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .findFirst()
                    .orElse("");
            return "ROLE_STAFF".equals(role);  // Kiểm tra xem có phải ROLE_STAFF không
        }
        return false;
    }
}
