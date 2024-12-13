package com.example.GymManagementSystem.service;

import com.example.GymManagementSystem.entity.TimeSlot;
import com.example.GymManagementSystem.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }
    public Optional<TimeSlot> getTimeSlotById(Integer id) {
        return timeSlotRepository.findById(id);
    }
    public TimeSlot createTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }
    public Optional<TimeSlot> updateTimeSlot(Integer id, TimeSlot timeSlot) {
      return timeSlotRepository.findById(id).map(timeSlotExits -> {
           timeSlotExits.setStartTime(timeSlot.getStartTime());
           timeSlotExits.setEndTime(timeSlot.getEndTime());
           return timeSlotRepository.save(timeSlotExits);
        });
    }
    public void deleteTimeSlot(Integer id) {
        timeSlotRepository.deleteById(id);
    }
  public Optional<TimeSlot> findByStartTimeAndEndTime(java.time.LocalTime startTime,java.time.LocalTime endTime){
       return timeSlotRepository.findByStartTimeAndEndTime(startTime, endTime);
  }
}