package com.example.GymManagementSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personaltrainer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String name;

    private Integer age;

    private String sex;

    private String phoneNumber;

    private LocalDate registerDate;

    private Integer category;

    private Integer wage;

    private String email;

    private Integer minSection;

    private Integer maxSection;

    private String field;

    private String description;
}
