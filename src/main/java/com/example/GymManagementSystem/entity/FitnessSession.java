package com.example.GymManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "fitness_session")
@Data
public class FitnessSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "n_th_session", nullable = false)
    private Integer nthSession;

    @ManyToOne
    @JoinColumn(name = "ID_customer_service", nullable = false)
    private CustomerService customerService;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "ID_time_slot", nullable = false)
    private TimeSlot timeSlot;

    @Column(name = "location", nullable = false)
    private String location;


}