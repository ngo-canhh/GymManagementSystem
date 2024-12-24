package com.example.GymManagementSystem.entity;

import java.util.List;

import com.example.GymManagementSystem.DTO.PTServiceDTo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personal_trainer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @OneToOne
    @JoinColumn(name = "ID_staff", referencedColumnName = "ID")
    private Staff staff;

    @Column(name = "field")
    private String field;

    @Column(name = "category")
    private int category;

    public PersonalTrainer(Staff staff) {
        this.staff = staff;
    }

    @Column(name = "min")
    private int min;

    @Column(name = "max")
    private int max;

    @Column(name = "description")
    private String description;

    @Transient
    private List<PTServiceDTo> services;
}
