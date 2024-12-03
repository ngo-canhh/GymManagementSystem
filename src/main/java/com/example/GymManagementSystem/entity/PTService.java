package com.example.GymManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personal_trainer_service")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PTService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @JoinColumn(name = "ID_PT", referencedColumnName = "ID")
    private PersonalTrainer personalTrainer;

    @JoinColumn(name = "ID_service", referencedColumnName = "ID")
    private Service service;
}
