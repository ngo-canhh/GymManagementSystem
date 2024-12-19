package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("SELECT s FROM Service s")
    List<Service> findAllServices();

    @Query("SELECT DISTINCT s.category FROM Service s")
    List<String> findDistinctCategory();

    @Query("SELECT s FROM Service s WHERE s.category = :category")
    List<Service> findByCategory(String category);

    @Query("SELECT s FROM Service s WHERE s.category = :name")
    <list>Service findServiceByCategoryName(String name);

    @Query("SELECT s FROM Service s WHERE s.ID = :serviceID")
    Service findServiceByID(@Param("serviceID") int serviceID);

    @Query("SELECT s FROM Service s WHERE s.name LIKE %:serviceName%")
    List<Service> findAllServicesByName(@Param("serviceName") String serviceName);

    @Query("SELECT s FROM Service s WHERE (s.category = :category OR :category = 'All') AND (s.status = :status OR :status = 'All')")
    List<Service> findAllServicesByCategoryAndStatus(@Param("category") String category, @Param("status") String status);
}
