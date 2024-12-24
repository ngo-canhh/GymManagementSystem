package com.example.GymManagementSystem.entity;

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
@Table(name = "position_information")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PositionInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "role")
    private String role;

    @Column(name = "department")
    private String department;

    @Column(name = "basic_salary")
    private double basic_salary;
}
