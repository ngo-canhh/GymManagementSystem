package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.CustomerService;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer> {

    List<CustomerService> findByCustomer(Customer customer);
    
}