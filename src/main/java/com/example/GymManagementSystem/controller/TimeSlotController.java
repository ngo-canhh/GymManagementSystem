package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.entity.TimeSlot;
import com.example.GymManagementSystem.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

  @Autowired
  private TimeSlotService timeSlotService;

  @GetMapping
  public ResponseEntity<List<TimeSlot>> getAllTimeSlots() {
    List<TimeSlot> timeSlots = timeSlotService.getAllTimeSlots();
    return new ResponseEntity<>(timeSlots, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getTimeSlotById(@PathVariable("id") Integer id) {
    Optional<TimeSlot> timeSlot = timeSlotService.getTimeSlotById(id);
    if (timeSlot.isPresent()) {
      return new ResponseEntity<>(timeSlot.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>("Không tìm thấy timeSlot id =" + id, HttpStatus.NOT_FOUND);
  }

  @PostMapping
  public ResponseEntity<?> createTimeSlot(@RequestBody TimeSlot timeSlot) {
    Optional<TimeSlot> timeSlotExist = timeSlotService.findByStartTimeAndEndTime(timeSlot.getStartTime(),
        timeSlot.getEndTime());
    if (timeSlotExist.isPresent()) {
      return new ResponseEntity<>("TimeSlot đã tồn tại trong hệ thống", HttpStatus.CONFLICT);
    }
    TimeSlot createdTimeSlot = timeSlotService.createTimeSlot(timeSlot);
    return new ResponseEntity<>(createdTimeSlot, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTimeSlot(@PathVariable("id") Integer id, @RequestBody TimeSlot timeSlot) {
    Optional<TimeSlot> updatedTimeSlot = timeSlotService.updateTimeSlot(id, timeSlot);
    if (updatedTimeSlot.isPresent()) {
      return new ResponseEntity<>(updatedTimeSlot.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>("Không tìm thấy timeSlot id = " + id, HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTimeSlot(@PathVariable("id") Integer id) {
    timeSlotService.deleteTimeSlot(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}