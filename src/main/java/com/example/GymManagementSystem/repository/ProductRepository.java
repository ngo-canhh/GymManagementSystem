package com.example.GymManagementSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    @Query("SELECT p FROM Product p WHERE p.ID= :ID")
    Product findProductById(@Param("ID") int ID);

    

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% OR :name = ''")
    List<Product> findAllProductsByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE (p.entry_date = :entry_date OR :entry_date IS NULL) AND (p.expiry_date = :expiry_date OR :expiry_date IS NULL) AND (p.status = :status OR :status = 'all')")
    List<Product> findAllProductsByDateAndStatus(@Param("entry_date") LocalDate entry_date, @Param("expiry_date") LocalDate expiry_date, @Param("status") String status);
}
