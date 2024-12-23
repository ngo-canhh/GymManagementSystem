package com.example.GymManagementSystem.DTO;

public class SalaryDTO {
    private Integer employeeId;  
    private int month;
    private int year;
    private double amount;

    public SalaryDTO(Integer employeeId, int month, int year, double amount) {
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
