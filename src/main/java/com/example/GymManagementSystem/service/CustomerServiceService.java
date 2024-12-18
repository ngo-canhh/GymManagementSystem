package com.example.GymManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.entity.CustomerService;

import com.example.GymManagementSystem.repository.CustomerServiceRepository;

@Service
public class CustomerServiceService {

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    public CustomerService getCustomerServiceById(Integer id) {
        return customerServiceRepository.findById(id).orElse(null);
    }
    
    
}
