package com.example.GymManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;           // Tên thiết bị
    private String category;       // Loại thiết bị (máy chạy bộ, tạ,...)
    private Integer quantity;      // Số lượng
    private Double price;          // Giá tiền mỗi thiết bị
    private String status;         // Trạng thái (mới, đã qua sử dụng,...)
    private String description;    // Mô tả thiết bị
}
