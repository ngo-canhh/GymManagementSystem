package com.example.GymManagementSystem.DTO;

import lombok.Data;
@Data
public class CustomerServiceDTO {
  private Integer id;
   private BillDTO bill;
   private PersonalTrainerServiceDTO personalTrainerService;
}