package com.example.GymManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.CustomerLogin;

@Repository
public interface CustomerLoginRepository extends JpaRepository<CustomerLogin, Integer> {

    Optional<CustomerLogin> findByUsername(String usernam);
}
