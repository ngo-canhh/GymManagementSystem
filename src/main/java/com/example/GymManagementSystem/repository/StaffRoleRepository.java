package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.GymManagementSystem.DTO.StaffDTO;
import com.example.GymManagementSystem.entity.StaffRole;

@Repository
public interface StaffRoleRepository extends JpaRepository<StaffRole, Integer> {
    @Query("SELECT sr FROM StaffRole sr ")
    List<StaffRole> findAllStaffRoles();

    @Query("SELECT sr FROM StaffRole sr WHERE sr.staff.full_name = :name ")
    List<StaffRole> findAllStaffRolesByName(@Param("name") String name);

    @Query("SELECT new com.example.GymManagementSystem.DTO.StaffDTO (sr.positionInformation.role, sr.create_date, sr.status) FROM StaffRole sr WHERE sr.staff.ID = :staffID")
    List<StaffDTO> findInforStaff(@Param("staffID") int staffID);

    @Query("SELECT sr FROM StaffRole sr WHERE sr.staff.ID = :staffID AND sr.status = 'Active'")
    StaffRole findActiveStaffRoleById(@Param("staffID") int staffID);

    @Query("SELECT sr FROM StaffRole sr WHERE (sr.staff.sex = :sex OR :sex = 'all') AND (sr.positionInformation.role = :role OR :role = 'all') AND (sr.status = :status OR :status = 'all')")
    List<StaffRole> findStaffBySexAndRoleAndStatus(@Param("sex") String sex, @Param("role") String role,
            @Param("status") String status);

    @Transactional
    @Modifying
    @Query("UPDATE StaffRole sr SET sr.status = 'Unactive' WHERE sr.ID = : ID")
    void unactiveStaffRole(@Param("ID") int ID);
}
