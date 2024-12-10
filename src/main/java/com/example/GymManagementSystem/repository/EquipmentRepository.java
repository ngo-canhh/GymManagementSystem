package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Query("SELECT e FROM Equipment e ")
    List<Equipment> findAllEquipment();

    @Query("SELECT e FROM Equipment e WHERE (e.brand = :brand OR :brand = 'all') AND (e.status = :status OR :status = 'all') AND (e.position = :position OR :position = 'all')")
    List<Equipment> findEquipmentByBrandStatusPosition(@Param("brand") String brand, @Param("status") String status, @Param("position") String position);
    
    @Query("SELECT e FROM Equipment e WHERE e.name LIKE %:name% ")
    List<Equipment> findEquipmentByName(@Param("name") String name);
}
