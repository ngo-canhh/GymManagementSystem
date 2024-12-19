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
@Table(name = "service")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "sale_price")
    private double sale_price;

    @Column(name = "numer_of_session")
    private int number_of_sessions;

    @Column(name = "frequency")
    private int frequency;

    @Column(name = "description")
    private String description;

    @Column(name = "pt_persentage")
    private double pt_persentage;

    @Column(name = "gym_persentage")
    private double gym_persentage;

    @Column(name = "status")
    private String status;
}