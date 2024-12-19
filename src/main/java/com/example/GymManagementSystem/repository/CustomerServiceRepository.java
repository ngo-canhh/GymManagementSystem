package com.example.GymManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.CustomerService;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer> {

    List<CustomerService> findByCustomer(Customer customer);
    Optional<CustomerService> findById(Integer id);
}
