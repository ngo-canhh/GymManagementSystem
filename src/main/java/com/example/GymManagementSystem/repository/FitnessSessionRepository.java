package com.example.GymManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.GymManagementSystem.entity.FitnessSession;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FitnessSessionRepository extends JpaRepository<FitnessSession, Integer> {
    List<FitnessSession> findByDate(LocalDate date);

    @Query(value = "SELECT fs.* FROM fitness_session fs " +
                   "JOIN customer_service cs ON fs.ID_customer_service = cs.ID " +
                   "JOIN customer c ON cs.ID_customer = c.ID " +
                   "JOIN time_slot ts ON fs.ID_time_slot = ts.ID " +
                   "WHERE c.ID = :customerId and fs.date >= current_date()" +
                   "ORDER BY fs.date ASC, ts.start_time ASC",
           nativeQuery = true)
    List<FitnessSession> findRemainingSessionsByCustomerId(@Param("customerId") int customerId);
}