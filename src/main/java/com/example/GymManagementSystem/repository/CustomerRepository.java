package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GymManagementSystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
