package com.example.GymManagementSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buoi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @EmbeddedId
    private SectionId id;

    @ManyToOne
    @JoinColumn(name = "ID_KH", insertable = false, updatable = false)
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "ID_PT", insertable = false, updatable = false)
    private Trainer trainer;


    private LocalDateTime begin;

    private LocalDateTime end;

    private String location;

}
