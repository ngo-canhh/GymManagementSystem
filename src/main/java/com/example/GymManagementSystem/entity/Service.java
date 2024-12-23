package com.example.GymManagementSystem.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

    @Transient
    private List<PersonalTrainer> pts;
}

// import jakarta.persistence.*;
// import lombok.Data;
// @Entity
// @Table(name = "service")
// @Data
// public class Service {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;
//     @Column(name="name", nullable = false)
//     private String name;
//     @Column(name="category", nullable = false)
//     private String category;
//     @Column(name = "sale_price", nullable = false)
//     private Double salePrice;
//     @Column(name="numer_of_session", nullable = false)
//     private Integer numberOfSession;
//     @Column(name="frequency", nullable = false)
//     private Integer frequency;
//     @Column(name="description", columnDefinition = "LONGTEXT")
//     private String description;
//     @Column(name="pt_persentage", nullable = false)
//     private Double ptPersentage;
//     @Column(name="gym_persentage", nullable = false)
//     private Double gymPersentage;
//     @Column(name = "status", nullable = false)
//     private String status;
// }