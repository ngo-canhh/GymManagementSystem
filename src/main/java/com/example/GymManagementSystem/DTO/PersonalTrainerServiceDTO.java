package com.example.GymManagementSystem.DTO;
import com.example.GymManagementSystem.entity.PersonalTrainer;

import lombok.Data;
@Data
public class PersonalTrainerServiceDTO{
private Integer id;
private ServiceDTO service;
private PersonalTrainer personalTrainer;
}