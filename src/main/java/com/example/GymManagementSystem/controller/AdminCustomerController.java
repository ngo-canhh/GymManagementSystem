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

import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.service.CustomerService;

@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String showAdminCustomerPage(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "AdminView/admin_customer";
    }

    @PostMapping("/add_new_customer")
    public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer) {
        customer.setCreate_date(LocalDate.now());
        Map<String, Object> respone = customerService.addNewCustomer(customer);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }

    @GetMapping("/customer_id")
    public ResponseEntity<?> getCustomerByID(@RequestParam int customerID) {
        Map<String, Object> respone = customerService.getCustomerByID(customerID);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }

    @PutMapping("/update_customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        Map<String, Object> respone = customerService.addNewCustomer(customer);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }

    @GetMapping("/filter_customer")
    public ResponseEntity<?> filterCustomer(@RequestParam String sex, @RequestParam int category){
        Map<String, Object> respone = customerService.getAllCustomersBySexAndCategory(sex, category);
        if((boolean) respone.get("success")){
            return ResponseEntity.ok(respone.get("data"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }
    @GetMapping("/searchCustomer")
    public ResponseEntity<?> searchCustomer(@RequestParam String NameOrPhone){
        Map<String, Object> respone = customerService.getAllCustomersByNameOrPhone(NameOrPhone);
        if((boolean) respone.get("success")){
            return ResponseEntity.ok(respone.get("data"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }
}
