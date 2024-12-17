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
@Table(name = "staff_role")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StaffRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "ID_staff", referencedColumnName = "ID")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "ID_role", referencedColumnName = "ID")
    private PositionInformation positionInformation;

    @Column(name = "create_date")
    private LocalDate create_date;

    public StaffRole(Staff staff, PositionInformation positionInformation, LocalDate create_date, String status) {
        this.staff = staff;
        this.positionInformation = positionInformation;
        this.create_date = create_date;
        this.status = status;
    }

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private String note;
}
