package com.example.GymManagementSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "date_of_birth")
    private LocalDateTime date_of_birth;

    @Column(name = "noID")
    private String noID;

    @Column(name = "email")
    private String email;

    @Column(name = "bank_account")
    private String bank_account;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "address")
    private String address;
}
