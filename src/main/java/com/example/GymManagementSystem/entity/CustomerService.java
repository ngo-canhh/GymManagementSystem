package com.example.GymManagementSystem.entity;

import java.time.LocalDate;

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
@Table(name = "customer_service")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "ID_customer", referencedColumnName = "ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ID_service", referencedColumnName = "ID")
    private PTService ptService;

    @ManyToOne
    @JoinColumn(name = "ID_bill", referencedColumnName = "ID")
    private Bill bill;

    @Column(name = "purchase_price")
    private double purchase_price;

    @Column(name = "purchase_date")
    private LocalDate purchase_date;

    @Column(name = "status")
    private String status;

    @Column(name = "remaining_sessions")
    private int remaining_sessions;
}
