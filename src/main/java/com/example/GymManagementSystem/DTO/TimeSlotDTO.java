package com.example.GymManagementSystem.DTO;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class TimeSlotDTO {
private Integer id;
private LocalTime startTime;
private LocalTime endTime;
}