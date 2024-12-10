package com.example.GymManagementSystem.controller;


import java.util.List;

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

import com.example.GymManagementSystem.entity.Service;
import com.example.GymManagementSystem.service.ServiceService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/service")
    public String showAdminServicePage(Model model){
        model.addAttribute("services", serviceService.getAllServices());
        return "AdminView/admin_service";
    }

    @GetMapping("/service/searchService")
    public ResponseEntity<?> searchService(@RequestParam("IDOrName") String IDOrName){
        IDOrName = IDOrName.trim();
        List<Service> services = serviceService.getAllServicesByIdOrName(IDOrName);
        return ResponseEntity.ok().body(services);
    }

    @GetMapping("/service/serviceID")
    public ResponseEntity<?> searchServiceID(@RequestParam("serviceID") int serviceID){
        Service service = serviceService.getServiceById(serviceID);
        if(service == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm tương ứng");
        }
        return ResponseEntity.ok(service);
    }

    @PostMapping("/service/add_new_service")
    public ResponseEntity<?> addNewService(@RequestBody Service service){
        Service savedService = serviceService.addNewService(service);
        return ResponseEntity.ok(savedService);
    }

    @GetMapping("/service/filter_service")
    public ResponseEntity<?> filterService(@RequestParam("category") String category, @RequestParam("status") String status){
        List<Service> services = serviceService.getAllServicesByCategoryAndStatus(category, status);
        return ResponseEntity.ok(services);
    }

    @PutMapping("/service/upadate_service")
    public ResponseEntity<?> updateService(@RequestBody Service service) {
        Service savedService = serviceService.addNewService(service);
        
        if (savedService == null) {
            // Trả về lỗi nếu không lưu thành công
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cập nhật dịch vụ thất bại.");
        }
        
        // Trả về dịch vụ đã cập nhật hoặc một thông báo thành công
        return ResponseEntity.ok(savedService); // Hoặc trả về một đối tượng thông báo nếu cần
    }
    
} 
