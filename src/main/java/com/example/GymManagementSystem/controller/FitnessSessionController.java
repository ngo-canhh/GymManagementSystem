package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.DTO.FitnessSessionDTO;
import com.example.GymManagementSystem.entity.FitnessSession;
import com.example.GymManagementSystem.entity.TimeSlot;
import com.example.GymManagementSystem.service.FitnessSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/fitness-sessions")
public class FitnessSessionController {

    @Autowired
    private FitnessSessionService fitnessSessionService;

    @GetMapping("/{date}")
    public ResponseEntity<List<FitnessSessionDTO>> getSessionsByDate(@PathVariable("date") LocalDate date) {
        List<FitnessSession> sessions = fitnessSessionService.getSessionsByDate(date);
        List<FitnessSessionDTO> sessionDTOs = sessions.stream()
                            .map(session -> {
                                FitnessSessionDTO sessionDTO = new FitnessSessionDTO();
                                sessionDTO.setId(session.getId());
                                sessionDTO.setCustomerName(session.getCustomerService().getCustomer().getFull_name());
                                sessionDTO.setCustomerPhoneNumber(session.getCustomerService().getCustomer().getPhonenumber());
                                sessionDTO.setStartTime(session.getTimeSlot().getStartTime());
                                sessionDTO.setEndTime(session.getTimeSlot().getEndTime());
                                sessionDTO.setDate(session.getDate());
                                sessionDTO.setLocation(session.getLocation());
                                sessionDTO.setPtName(session.getCustomerService().getPtService().getPersonalTrainer().getStaff().getFull_name());
                                sessionDTO.setNthSession(session.getNthSession());

                                return sessionDTO;
                            }).toList();
        return new ResponseEntity<>(sessionDTOs, HttpStatus.OK);
    }

    @GetMapping("/remaining-sessions/{customerId}")
    public ResponseEntity<List<FitnessSessionDTO>> getSessionsByDate(@PathVariable("customerId") Integer customerId) {
        List<FitnessSession> sessions = fitnessSessionService.getRemainingSessionsByCustomerId(customerId);
        List<FitnessSessionDTO> sessionDTOs = sessions.stream()
                            .map(session -> {
                                FitnessSessionDTO sessionDTO = new FitnessSessionDTO();
                                sessionDTO.setId(session.getId());
                                sessionDTO.setCustomerName(session.getCustomerService().getCustomer().getFull_name());
                                sessionDTO.setCustomerPhoneNumber(session.getCustomerService().getCustomer().getPhonenumber());
                                sessionDTO.setStartTime(session.getTimeSlot().getStartTime());
                                sessionDTO.setEndTime(session.getTimeSlot().getEndTime());
                                sessionDTO.setDate(session.getDate());
                                sessionDTO.setLocation(session.getLocation());
                                sessionDTO.setPtName(session.getCustomerService().getPtService().getPersonalTrainer().getStaff().getFull_name());
                                sessionDTO.setNthSession(session.getNthSession());
                                return sessionDTO;
                            }).toList();
        return new ResponseEntity<>(sessionDTOs, HttpStatus.OK);
    }

    @GetMapping("/available-time-slots/{serviceId}/{date}")
    public ResponseEntity<?> getAvailableTimeSlots(
            @PathVariable("serviceId") Integer serviceId,
            @PathVariable("date") LocalDate date
    ) {
      List<TimeSlot> availableTimeSlots = fitnessSessionService.getAvailableTimeSlots(serviceId, date);
        if(availableTimeSlots == null){
          return new ResponseEntity<>("Không tìm thấy huấn luyện viên của gói dịch vụ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(availableTimeSlots, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<FitnessSession> createFitnessSession(@RequestBody FitnessSession session) {
        FitnessSession createdSession = fitnessSessionService.createFitnessSession(session);
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFitnessSession(
            @PathVariable("id") Integer id,
            @RequestBody FitnessSession session) {
      Optional<FitnessSession> updatedSession = fitnessSessionService.updateFitnessSession(session, id);
      if(updatedSession.isPresent()){
         return new ResponseEntity<>(updatedSession.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>("Không tìm thấy buổi tập để cập nhật.", HttpStatus.NOT_FOUND);

    }

}