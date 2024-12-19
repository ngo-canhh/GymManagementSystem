package com.example.GymManagementSystem.DTO;

import java.util.List;

import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.Product;

public class OrderRequest {
    private Customer customer;
    private List<Product> orderDetails;
    private int staffID;
}
