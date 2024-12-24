package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.example.GymManagementSystem.entity.TimeSlot;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
  Optional<TimeSlot> findByStartTimeAndEndTime(java.time.LocalTime startTime,java.time.LocalTime endTime);
}