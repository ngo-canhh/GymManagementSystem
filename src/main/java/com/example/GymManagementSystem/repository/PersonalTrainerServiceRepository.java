package com.example.GymManagementSystem.repository;

import com.example.GymManagementSystem.entity.PTService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonalTrainerServiceRepository extends JpaRepository<PTService, Integer> {
    List<PTService> findByService_ID(Integer serviceId);
}