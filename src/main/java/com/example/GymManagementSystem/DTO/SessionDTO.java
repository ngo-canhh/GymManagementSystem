package com.example.GymManagementSystem.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionDTO {
    private Integer timeSlotId;
    private LocalDate date;

    @Override
    public String toString() {
        return "SessionDTO{" +
                "timeSlotId=" + timeSlotId +
                ", date=" + date +
                '}';
    }
}
