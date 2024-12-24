package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.DTO.SessionDTO;
import com.example.GymManagementSystem.entity.FitnessSession;
import com.example.GymManagementSystem.entity.Service;
import com.example.GymManagementSystem.entity.TimeSlot;
import com.example.GymManagementSystem.service.CustomerServiceService;
import com.example.GymManagementSystem.service.FitnessSessionService;
import com.example.GymManagementSystem.service.ServiceService;
import com.example.GymManagementSystem.service.TimeSlotService;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private FitnessSessionService fitnessSessionService;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/make-schedule/{serviceId}")
    public String makeSchedule(Model model, @PathVariable("serviceId") Integer serviceId) {
        LocalDate curDate = LocalDate.now();
        List<ScheduleData> scheduleDataList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < 7; ++i) {
            LocalDate date = curDate.plusDays(i);
            List<TimeSlot> availableTimeSlots = fitnessSessionService.getAvailableTimeSlots(serviceId, date);
            if (Objects.nonNull(availableTimeSlots) && availableTimeSlots.size() > 0) {
                ScheduleData data = new ScheduleData(date.format(formatter), date, availableTimeSlots);
                scheduleDataList.add(data);
            }
        }
        Service service = serviceService.getServiceById(serviceId);

        model.addAttribute("frequency", service.getFrequency());
        model.addAttribute("numberOfSessions", service.getNumber_of_sessions());
        model.addAttribute("scheduleDataList", scheduleDataList);
        model.addAttribute("serviceId", serviceId);
        return "make-schedule";
    }

    @PostMapping("/make-schedule/submit")
    public ResponseEntity<?> submitSchedule(@RequestParam Integer ID_customer_service, @RequestBody List<SessionDTO> sessions) {

        // sessions.forEach(item -> {
        //     System.out.println(item.toString());
        // });
        List<FitnessSession> toSave = new ArrayList<>();
        for (int i = 0; i < sessions.size(); ++i) {
            SessionDTO session = sessions.get(i);

            // System.out.println(session.getDate());
            // System.out.println(session.getTimeSlotId());
            // System.out.println("-----------");
            FitnessSession fitnessSession = new FitnessSession(
                i+1,
                customerServiceService.getCustomerServiceById(ID_customer_service),
                session.getDate(),
                timeSlotService.getTimeSlotById(session.getTimeSlotId()).get(),
                "Ha Noi"
            );
            toSave.add(fitnessSession);

        }

        fitnessSessionService.createFitnessSessions(toSave);
        return ResponseEntity.status(HttpStatus.OK).body(toSave);
    }



    @Data
    @AllArgsConstructor
    static class ScheduleData {
        private String formattedDate;
        private LocalDate date;
        private List<TimeSlot> timeSlots;
    }
}