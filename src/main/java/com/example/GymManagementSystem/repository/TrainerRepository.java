package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GymManagementSystem.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    
}
