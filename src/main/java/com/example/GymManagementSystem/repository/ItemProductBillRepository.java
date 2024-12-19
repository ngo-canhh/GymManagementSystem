package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GymManagementSystem.entity.IteamProductBill;

public interface ItemProductBillRepository extends JpaRepository<IteamProductBill, Integer> {
    
    
}
