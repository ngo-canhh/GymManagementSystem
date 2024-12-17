package com.example.GymManagementSystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.DTO.PTServiceDTo;
import com.example.GymManagementSystem.entity.PTService;
import com.example.GymManagementSystem.entity.PersonalTrainer;
import com.example.GymManagementSystem.repository.PersonalTrainerRepository;

@Service
public class PersonalTrainerService {
    @Autowired
    private PersonalTrainerRepository personalTrainerRepository;

    @Autowired
    private PTServiceService ptServiceService;

    public Map<String, Object> getAllPT() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", personalTrainerRepository.findAllPersonalTrainers());
            response.put(("success"), true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getPTByID(int ID) {
        Map<String, Object> response = new HashMap<>();
        try {
            PersonalTrainer pt = personalTrainerRepository.findPersonalTrainerById(ID);
            Map<String, Object> ptService = ptServiceService.getAllServicesOfPTByID(ID);
            if ((boolean) ptService.get("success")) {
                pt.setServices((List<PTServiceDTo>) ptService.get("data"));
            }
            response.put("data", pt);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> getAllPTByName(String name) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", personalTrainerRepository.findPersonalTrainerByName(name));
            response.put("success", true);
        } catch (Exception e) {
            // TODO: handle exception
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> upadatePT(PersonalTrainer pTrainer) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> response2 = ptServiceService.getAllServicesOfPTByID(pTrainer.getID());
            if ((boolean) response2.get("success")) {
                @SuppressWarnings("unchecked")
                List<PTServiceDTo> existtingService = (List<PTServiceDTo>) response2.get("data");
                List<PTServiceDTo> newService = pTrainer.getServices();
                List<PTServiceDTo> newServiceList = new ArrayList<>();

                Map<Integer, PTServiceDTo> serviceMap = new HashMap<>();
                for (PTServiceDTo ptServiceDTo : newService) {
                    serviceMap.put(ptServiceDTo.getService().getID(), ptServiceDTo);
                    newServiceList.add(ptServiceDTo);
                }

                for (PTServiceDTo ptServiceDTo : existtingService) {
                    if(!serviceMap.containsKey(ptServiceDTo.getService().getID())){
                        ptServiceDTo.setStatus("Ngừng hoạt động");
                        newServiceList.add(ptServiceDTo);
                    }
                }

                for (PTServiceDTo ptServiceDTo : newServiceList) {
                    PTService ptService = ptServiceService.getALlPTServiceByPTAndService(pTrainer.getID(), ptServiceDTo.getService().getID());
                    if(ptService == null){
                        ptService = new PTService(pTrainer, ptServiceDTo.getService(), ptServiceDTo.getStatus());
                        ptServiceService.addPTService(ptService);
                    } else {
                        ptService.setStatus(ptServiceDTo.getStatus());
                        ptServiceService.addPTService(ptService);
                    }
                }
                response.put("data", ptServiceService.getAllServicesOfPTByID(pTrainer.getID()));
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "No services found for this PT");
            }

        } catch (Exception e) {
            // TODO: handle exception
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
}
