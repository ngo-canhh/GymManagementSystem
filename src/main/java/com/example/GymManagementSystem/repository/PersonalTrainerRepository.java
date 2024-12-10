package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.PersonalTrainer;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Integer>{
    @Query("SELECT pt FROM PersonalTrainer pt")
    List<PersonalTrainer> findAllPersonalTrainers();

    @Query("SELECT pt FROM PersonalTrainer pt WHERE pt.staff.full_name LIKE  %:name%")
    List<PersonalTrainer> findPersonalTrainerByName(@Param("name") String name);

    @Query("SELECT pt FROM PersonalTrainer pt WHERE (pt.category = :category OR :category = -1) AND (pt.field = :field OR :field = 'all')")
    List<PersonalTrainer> findPersonalTrainerByCategoryAndField(@Param("category") int category, @Param("field") String field);

    @Query("SELECT pt FROM PersonalTrainer pt WHERE pt.ID = :ID")
    List<PersonalTrainer> findPersonalTrainerById(@Param("ID") int ID);
}
