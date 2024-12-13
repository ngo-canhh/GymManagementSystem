package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.GymManagementSystem.entity.FitnessSession;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FitnessSessionRepository extends JpaRepository<FitnessSession, Integer> {
    List<FitnessSession> findByDate(LocalDate date);
}