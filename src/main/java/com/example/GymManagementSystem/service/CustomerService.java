package com.example.GymManagementSystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.repository.CustomerRepository;
import com.example.GymManagementSystem.repository.CustomerServiceRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    public Map<String, Object> getAllCustomersByName(String name) {
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", customerRepository.findAllCustomersByName(name));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public Map<String, Object> getCustomerByID(int ID){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", customerRepository.findCustomerByID(ID));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }


    public Map<String, Object> addNewCustomer(Customer customer){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("success", true);
            respone.put("data", customerRepository.save(customer));
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public Map<String, Object> getAllCustomersByNameOrPhone(String seacrh_str){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", customerRepository.findAllCustomersByNameOrPhone(seacrh_str));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public Map<String, Object> getAllCustomersBySexAndCategory(String sex, int category){
        Map<String, Object> respone = new HashMap<>();
        try {
            respone.put("data", customerRepository.findAllCustomersBySexAndCategory(sex, category));
            respone.put("success", true);
        } catch (Exception e) {
            respone.put("success", false);
            respone.put("message", e.getMessage());
        }
        return respone;
    }

    public List<com.example.GymManagementSystem.entity.CustomerService> getCustomerServiceByCustomer(Customer customer){
        return customerServiceRepository.findByCustomer(customer);
    }
}
