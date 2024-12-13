package com.example.GymManagementSystem.DTO;

import java.time.LocalTime;
import lombok.Data;
@Data
public class TimeSlotDTO {
private Integer id;
private LocalTime startTime;
private LocalTime endTime;
}