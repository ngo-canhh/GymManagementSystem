package com.example.GymManagementSystem.service;

import com.example.GymManagementSystem.entity.*;
import com.example.GymManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FitnessSessionService {

    @Autowired
    private FitnessSessionRepository fitnessSessionRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private PersonalTrainerServiceRepository personalTrainerServiceRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<FitnessSession> getSessionsByDate(LocalDate date) {
        return fitnessSessionRepository.findByDate(date);
    }
    

    public List<TimeSlot> getAvailableTimeSlots(Integer serviceId, LocalDate date){
        // Lấy danh sách PT của gói tập
        List<PTService> personalTrainers = personalTrainerServiceRepository.findByService_ID(serviceId);
        if(personalTrainers.size() == 0){
           return null;
        }
        List<Integer> trainerIds = personalTrainers.stream()
                .map(pts -> pts.getPersonalTrainer().getStaff().getID())
                .toList();
        // Lấy danh sách các khung giờ đã được đặt của các PT trong gói tập trong ngày
        List<Integer> occupiedTimeSlots = fitnessSessionRepository.findByDate(date).stream()
                .filter(session -> personalTrainers.stream().anyMatch(pts -> pts.getPersonalTrainer().getStaff().getID() == 
                        session.getCustomerService().getPtService().getPersonalTrainer().getStaff().getID()
                ))
                .map(session -> session.getTimeSlot().getId()).toList();
        // Lấy tất cả các khung giờ
        List<TimeSlot> allTimeSlots = timeSlotRepository.findAll();
        // Lọc ra các khung giờ còn trống
        List<TimeSlot> availableTimeSlots = allTimeSlots.stream().filter(slot -> !occupiedTimeSlots.contains(slot.getId())).toList();
      return availableTimeSlots;
    }
    public FitnessSession createFitnessSession(FitnessSession session){
      return fitnessSessionRepository.save(session);
    }
    public Optional<FitnessSession> updateFitnessSession(FitnessSession session, Integer id){
        return fitnessSessionRepository.findById(id).map(fitnessSession -> {
            fitnessSession.setCustomerService(session.getCustomerService());
            fitnessSession.setDate(session.getDate());
            fitnessSession.setLocation(session.getLocation());
            fitnessSession.setNthSession(session.getNthSession());
            fitnessSession.setTimeSlot(session.getTimeSlot());
            return fitnessSessionRepository.save(fitnessSession);
        });
    }
}