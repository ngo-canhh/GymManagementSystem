package com.example.GymManagementSystem.DTO;

public class SalaryDTO {
    private Integer staffId;  
    private int month;
    private int year;
    private double salaryAmount;

    public SalaryDTO(Integer staffId, int month, int year, double salaryAmount) {
        this.staffId = staffId;
        this.month = month;
        this.year = year;
        this.salaryAmount = salaryAmount;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }
}
