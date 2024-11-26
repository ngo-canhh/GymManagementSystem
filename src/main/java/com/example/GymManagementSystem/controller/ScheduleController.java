package com.example.GymManagementSystem.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GymManagementSystem.entity.Section;
import com.example.GymManagementSystem.service.SectionService;

@Controller
public class ScheduleController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("/schedule")
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
        return "StaffViewsHtml/schedule";
    }

    // @GetMapping("/schedule/day")
    // public String getDayTimeline(@RequestParam(value = "day") int day,
    //                              @RequestParam(value = "month") int month,
    //                              @RequestParam(value = "year") int year,
    //                              Model model) {

    //     LocalDate date = LocalDate.of(year, month, day);
    //     List<Section> sections = sectionService.findAllSectionsByDate(date);
    //     model.addAttribute("sections", sections);
    //     model.addAttribute("day", day);
    //     model.addAttribute("month", month);
    //     model.addAttribute("year", year);
    //     return "day-timeline";                          
    // }

    @GetMapping("/schedule/date/{day}/{month}/{year}")
    public String getDayTimeline(@PathVariable(value = "day") int day, 
                                 @PathVariable(value = "month") int month,
                                 @PathVariable(value = "year") int year,
                                    Model model) {
        LocalDate localDate = LocalDate.of(year, month, day);
        List<Section> sections = sectionService.findAllSectionsByDate(localDate);
        model.addAttribute("sections", sections);
        model.addAttribute("day", localDate.getDayOfMonth());
        model.addAttribute("month", localDate.getMonthValue());
        model.addAttribute("year", localDate.getYear());
        return "StaffViewsHtml/day-timeline";  
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
