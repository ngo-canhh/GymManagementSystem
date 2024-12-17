package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.GymManagementSystem.DTO.PTServiceDTo;
import com.example.GymManagementSystem.entity.PTService;

@Repository
public interface PTServiceRepository extends JpaRepository<PTService, Integer> {
    @Query("SELECT new com.example.GymManagementSystem.DTO.PTServiceDTo(ps.service, ps.status) FROM PTService ps WHERE ps.personalTrainer.ID = :ID")
    List<PTServiceDTo> findAllServicesOfPTByID(@Param("ID") int id);

    @Query("SELECT ps FROM PTService ps WHERE ps.personalTrainer.ID = :ID AND ps.service.ID = :sID")
    PTService findServiceOfPTByIDAndService(@Param("ID") int id, @Param("sID") int sid);

    @Transactional
    @Modifying
    @Query("DELETE FROM PTService ps WHERE ps.personalTrainer.ID = :ID")
    void deletePTServiceByIDPT(@Param("ID") int ID);

} 