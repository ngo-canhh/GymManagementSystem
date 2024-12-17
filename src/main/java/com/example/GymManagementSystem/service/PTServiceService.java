package com.example.GymManagementSystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.entity.PTService;
import com.example.GymManagementSystem.repository.PTServiceRepository;

@Service
public class PTServiceService {
    @Autowired
    private PTServiceRepository ptServiceRepository;

    public Map<String, Object> getAllServicesOfPTByID(int id){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", ptServiceRepository.findAllServicesOfPTByID(id));
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    public void deletePTServiceByIDPT(int ID){
        ptServiceRepository.deletePTServiceByIDPT(ID);
    }

    public PTService getALlPTServiceByPTAndService(int ptID, int serviceID){
        return ptServiceRepository.findServiceOfPTByIDAndService(ptID, serviceID);
    }

    public Map<String, Object> addPTService(PTService ptService){
        Map<String, Object> response = new HashMap<>();
        try {
            PTService savedPtService = ptServiceRepository.save(ptService);
            response.put("data", savedPtService);
            response.put("success", true);
        } catch (Exception e) {
            // TODO: handle exception
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
