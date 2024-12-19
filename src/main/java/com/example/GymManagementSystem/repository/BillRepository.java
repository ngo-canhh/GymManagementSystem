package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GymManagementSystem.entity.Bill;
import com.example.GymManagementSystem.entity.Customer;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill findBillByCustomerAndStatus(Customer customer, String status);
}
