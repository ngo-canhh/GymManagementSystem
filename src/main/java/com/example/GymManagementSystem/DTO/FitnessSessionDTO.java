package com.example.GymManagementSystem.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import lombok.Data;

@Data
public class FitnessSessionDTO {
    private Integer id;
    private Integer nthSession;
    private String customerName;
    private String ptName;
    private String customerPhoneNumber;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    public String getFormattedStartTime() {
        return startTime != null ? startTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "";
    }

    public String getFormattedEndTime() {
        return endTime != null ? endTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "";
    }
}