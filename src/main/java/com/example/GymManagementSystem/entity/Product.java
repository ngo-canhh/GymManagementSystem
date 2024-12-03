package com.example.GymManagementSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "purchase_price")
    private double purchase_price;

    @Column(name = "sale_price")
    private double sale_price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "entry_date")
    private LocalDate entry_date;

    @Column(name = "expiry_date")
    private LocalDate expiry_date;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;
}
