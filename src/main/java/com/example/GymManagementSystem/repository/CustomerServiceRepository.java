package com.example.GymManagementSystem.repository;

import com.example.GymManagementSystem.entity.CustomerService;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer> {
    Optional<CustomerService> findById(Integer id);
}
