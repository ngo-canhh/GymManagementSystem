package com.example.GymManagementSystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GymManagementSystem.entity.PersonalTrainer;
import com.example.GymManagementSystem.entity.Service;
import com.example.GymManagementSystem.service.PersonalTrainerService;
import com.example.GymManagementSystem.service.ServiceService;

@Controller
@RequestMapping("/admin/pt")
public class AdminPTController {

    @Autowired
    private PersonalTrainerService personalTrainerService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("")
    public String showPTPage(Model model) {
        Map<String, Object> response = personalTrainerService.getAllPT();
        if ((boolean) response.get("success")) {
            model.addAttribute("pts", response.get("data"));
        }
        return "AdminView/admin_personal_trainer";
    }

    @GetMapping("/PTDetails")
    public ResponseEntity<?> getInforPT(@RequestParam int ptID) {
        Map<String, Object> response = personalTrainerService.getPTByID(ptID);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        }
        return ResponseEntity.badRequest().body(response.get("message"));
    }

    @GetMapping("/getAllService")
    public ResponseEntity<?> getAllService() {
        try {
            List<Service> response = serviceService.getAllServices();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/searchPT")
    public ResponseEntity<?> getPT(@RequestParam String name) {
        Map<String, Object> response = personalTrainerService.getAllPTByName(name);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        }
        return ResponseEntity.badRequest().body(response.get("message"));
    }

    @PostMapping("/updatePT")
    public ResponseEntity<?> updatePT(@RequestBody PersonalTrainer pt){
        Map<String, Object> response = personalTrainerService.upadatePT(pt);
        if ((boolean) response.get("success")) {
            return ResponseEntity.ok(response.get("data"));
        }
        return ResponseEntity.badRequest().body(response.get("message"));
    }
}
