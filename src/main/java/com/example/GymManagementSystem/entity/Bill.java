package com.example.GymManagementSystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "ID_staff")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "ID_customer")
    private Customer customer;

    @Column(name = "create_date")
    private LocalDateTime create_date;

    @Column(name = "total_amount")
    private double total_amount;

    @Column(name = "payment_method")
    private String payment_method;

    @Column(name = "status")
    private String status;
}
