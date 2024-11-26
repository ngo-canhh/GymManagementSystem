package com.example.GymManagementSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.GymManagementSystem.entity.Section;
import com.example.GymManagementSystem.entity.SectionId;

public interface SectionRepository extends JpaRepository<Section, SectionId> {
    @Query("SELECT s FROM Section s WHERE FUNCTION('DATE', s.begin) = :date")
    List<Section> findAllSectionsByDate(@Param("date") LocalDate date);
}

