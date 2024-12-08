package com.example.GymManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.GymManagementSystem.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();

    @Query("SELECT c FROM Customer c WHERE c.full_name = :name")
    List<Customer> findAllCustomersByName(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE c.ID = :ID")
    Customer findCustomerByID(@Param("ID") int ID);

    @Query("SELECT c FROM Customer c WHERE (c.sex = :sex OR :sex = 'all') and (c.category = :category OR :category = -1)")
    List<Customer> findAllCustomersBySexAndCategory(@Param("sex") String sex, @Param("category") int category);

    @Query("SELECT c FROM Customer c WHERE (c.full_name = :search_str) OR (c.phonenumber = :search_str)")
    List<Customer> findAllCustomersByNameOrPhone(@Param("search_str") String search_str);
}
