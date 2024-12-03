package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT s FROM Service s")
    List<Service> findAllServices();

    @Query("SELECT s FROM Service s WHERE s.ID = :serviceID OR s.name = :serviceName")
    List<Service> findAllServicesByIDOrName(@Param("serviceID") int serviceID, @Param("serviceName") String serviceName);
}
