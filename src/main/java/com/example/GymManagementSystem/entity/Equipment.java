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
@Table(name = "equipment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "purchase_price")
    private double purchase_price;

    @Column(name = "buy_date")
    private LocalDate buy_date;

    @Column(name = "warranty")
    private int warranty;

    @Column(name = "status")
    private String status;

    @Column(name = "position")
    private String position;

    @Column(name = "quantity")
    private int quantity;
}
