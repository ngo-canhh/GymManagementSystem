package com.example.GymManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.GymManagementSystem.entity.Service;
import com.example.GymManagementSystem.repository.ServiceRepository;

@org.springframework.stereotype.Service
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

    public Service addNewService(Service service){
        return serviceRepository.save(service);
    }

    public List<Service> getAllServicesByCategoryAndStatus(String category, String status){
        return serviceRepository.findAllServicesByCategoryAndStatus(category, status);
    }
}
