package com.example.GymManagementSystem.controller;

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

import com.example.GymManagementSystem.entity.Equipment;
import com.example.GymManagementSystem.service.EquipmentService;

@Controller
@RequestMapping("/admin/equipment")
public class AdminEquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("")
    public String showAdminEquipmentPage(Model Model) {
        Map<String, Object> response = equipmentService.getAllEquipment();
        if ((boolean) response.get("success")) {
            Model.addAttribute("equipments", response.get("data"));
        }
        return "AdminView/admin_equipment";
    }

    @PostMapping("/add_new_equipment")
    public ResponseEntity<?> addNewEquipment(@RequestBody Equipment equipment) {
        Map<String, Object> response = equipmentService.addNewEquipment(equipment);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
    }

    @PutMapping("/update_equipment")
    public ResponseEntity<?> updateEquipment(@RequestBody Equipment equipment) {
        Map<String, Object> response = equipmentService.updateEquipment(equipment);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
    }

    @GetMapping("/filter_equipment")
    public ResponseEntity<?> filterEquipment(@RequestParam String filterBrand, @RequestParam String filterStatus, @RequestParam String filterPosition){
        Map<String, Object> response = equipmentService.filterEquipment(filterBrand, filterStatus, filterPosition);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
    }

    @GetMapping("/search_equipment")
    public ResponseEntity<?> filterEquipment(@RequestParam String searchValue){
        Map<String, Object> response = equipmentService.searchEquipment(searchValue);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.get("message"));
    }

}
