package com.example.GymManagementSystem.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.GymManagementSystem.DTO.FitnessSessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GymManagementSystem.service.FitnessSessionService;

@Controller
public class ScheduleController {

    @Autowired
    private FitnessSessionService fitnessSessionService;

    @GetMapping("/staff/schedule")
    public String showCalendar(@RequestParam(value = "month", defaultValue = "0") int month,
                               @RequestParam(value = "year", defaultValue = "0") int year,
                               Model model) {
        if (month == 0 && year == 0) {
            month = LocalDate.now().getMonth().getValue();
            year = LocalDate.now().getYear();
        }
        List<List<String>> calendar = generateCalendar(month, year);
        model.addAttribute("calendar", calendar);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "StaffViews/schedule";
    }

    @GetMapping("/staff/schedule/date/{day}/{month}/{year}")
    public String getDayTimeline(@PathVariable(value = "day") int day,
                                 @PathVariable(value = "month") int month,
                                 @PathVariable(value = "year") int year,
                                 Model model) {
        LocalDate localDate = LocalDate.of(year, month, day);
        List<FitnessSessionDTO> fitnessSessions = fitnessSessionService.getSessionsByDate(localDate)
                .stream()
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
                }).collect(Collectors.toList());
        model.addAttribute("fitnessSessions", fitnessSessions);
        model.addAttribute("day", localDate.getDayOfMonth());
        model.addAttribute("month", localDate.getMonthValue());
        model.addAttribute("year", localDate.getYear());
        return "StaffViews/day-timeline";
    }

    private List<List<String>> generateCalendar(int month, int year) {
        List<List<String>> weeks = new ArrayList<>();
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int daysInMonth = firstDay.lengthOfMonth();
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue();

        List<String> week = new ArrayList<>();
        for (int i = 1; i < firstDayOfWeek; i++) {
            week.add("");
        }

        for (int day = 1; day <= daysInMonth; day++) {
            week.add(String.valueOf(day));
            if (week.size() == 7) {
                weeks.add(week);
                week = new ArrayList<>();
            }
        }

        while (week.size() < 7) {
            week.add("");
        }
        weeks.add(week);

        return weeks;
    }
}