package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.PositionInformation;

@Repository
public interface PositionInformationRepository extends JpaRepository<PositionInformation, Integer> {
    @Query("SELECT pi FROM PositionInformation pi WHERE pi.role = :role")
    PositionInformation findPositionInformationByRole(@Param("role") String role);
}