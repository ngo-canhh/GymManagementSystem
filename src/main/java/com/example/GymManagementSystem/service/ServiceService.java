package com.example.GymManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GymManagementSystem.repository.ServiceRepository;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public List<com.example.GymManagementSystem.entity.Service> getAllServices(){
        return serviceRepository.findAllServices();
    }

    public List<com.example.GymManagementSystem.entity.Service> getAllServicesByIdOrName(String serviceSearch){
        return serviceRepository.findAllServicesByName(serviceSearch); 
    }

    public com.example.GymManagementSystem.entity.Service getServiceById(int serviceID){
        return serviceRepository.findServiceByID(serviceID);
    }
}
