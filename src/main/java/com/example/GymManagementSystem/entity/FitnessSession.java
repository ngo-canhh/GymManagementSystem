package com.example.GymManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "fitness_session")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FitnessSession {
    public FitnessSession(Integer nthSession, CustomerService customerService, LocalDate date, TimeSlot timeSlot,
            String location) {
        this.nthSession = nthSession;
        this.customerService = customerService;
        this.date = date;
        this.timeSlot = timeSlot;
        this.location = location;
    }

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