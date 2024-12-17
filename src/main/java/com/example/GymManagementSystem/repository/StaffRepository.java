package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT s FROM Staff s")
    List<Staff> findAllStaff();

    @Query("SELECT s FROM Staff s WHERE s.ID = :staffID")
    Staff findStaffByID(@Param("staffID") int staffID);
}
