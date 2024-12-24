package com.example.GymManagementSystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.repository.PositionInformationRepository;

@Service
public class PositionInformationService {
    @Autowired
    private PositionInformationRepository positionInformationRepository;

    public Map<String, Object> getPositionInformationByRole(String role){
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("data", positionInformationRepository.findPositionInformationByRole(role));
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
