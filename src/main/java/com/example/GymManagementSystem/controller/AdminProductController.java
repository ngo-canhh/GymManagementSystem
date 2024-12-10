package com.example.GymManagementSystem.controller;

import java.time.LocalDate;
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

import com.example.GymManagementSystem.entity.Product;
import com.example.GymManagementSystem.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String showAdminProductPage(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "AdminView/admin_product";
    }

    @PostMapping("/add_new_product")
    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
        Map<String, Object> respone = productService.addNewProduct(product);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }

    @GetMapping("/productID")
    public ResponseEntity<?> getProductByID(@RequestParam int productID){
        Map<String, Object> respone = productService.getProductByID(productID);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }

    @PutMapping("/upadate_product")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        Map<String, Object> respone = productService.updateProduct(product);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }

    @GetMapping("/filter_product")
    public ResponseEntity<?> filterProduct(@RequestParam String entry_date, @RequestParam String expiry_date, @RequestParam String status){
        LocalDate entryDate = entry_date.isEmpty() ? null : LocalDate.parse(entry_date);
        LocalDate expiryDate = expiry_date.isEmpty() ? null : LocalDate.parse(expiry_date);
        Map<String, Object> respone = productService.filterProduct(entryDate, expiryDate, status);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }

    }

    @GetMapping("/searchProduct")
    public ResponseEntity<?> searchProduct(@RequestParam String name){
        Map<String, Object> respone = productService.getProductsByName(name);
        if ((boolean) respone.get("success")) {
            return ResponseEntity.ok(respone.get("data"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone.get("message"));
        }
    }
}
