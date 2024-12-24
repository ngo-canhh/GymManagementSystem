package com.example.GymManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.GymManagementSystem.entity.StaffLogin;

public interface StaffLoginRepository extends JpaRepository<StaffLogin, Integer> {
    Optional<StaffLogin> findByUsername(String username);
}
