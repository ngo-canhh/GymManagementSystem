package com.example.GymManagementSystem.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.entity.Product;
import com.example.GymManagementSystem.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAllProducts();
    }

    public Map<String, Object> getProductsByName(String name){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", productRepository.findAllProductsByName(name));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public Map<String, Object> addNewProduct(Product product){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", productRepository.save(product));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public Map<String, Object> updateProduct(Product product){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", productRepository.save(product));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public Map<String, Object> getProductByID(int productID){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", productRepository.findProductById(productID));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public Map<String, Object> filterProduct(LocalDate entryDate, LocalDate expiryDate, String status){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", productRepository.findAllProductsByDateAndStatus(entryDate, expiryDate, status));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    
}
