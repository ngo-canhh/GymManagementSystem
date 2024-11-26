package com.example.GymManagementSystem.service;

import java.time.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.entity.Section;
import com.example.GymManagementSystem.repository.SectionRepository;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> findAllSectionsByDate(LocalDate date) {
        return sectionRepository.findAllSectionsByDate(date);
    }
}
