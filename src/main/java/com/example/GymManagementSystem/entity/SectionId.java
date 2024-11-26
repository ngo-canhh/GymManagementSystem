package com.example.GymManagementSystem.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionId implements Serializable {

    @Column(name = "ID_KH", nullable = false)
    private Integer customerId;

    @Column(name = "ID_PT", nullable = false)
    private Integer trainerId;
}