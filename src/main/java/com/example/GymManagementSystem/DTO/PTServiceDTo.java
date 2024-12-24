package com.example.GymManagementSystem.DTO;

import com.example.GymManagementSystem.entity.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PTServiceDTo {
    private Service service;
    private String status;
}
