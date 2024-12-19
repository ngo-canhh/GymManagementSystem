package com.example.GymManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_product_bill")
@Setter
@Getter
@NoArgsConstructor

public class IteamProductBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "ID_bill", referencedColumnName = "ID")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "ID_product", referencedColumnName = "ID")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "purchase_price")
    private double purchase_price;

    @Column(name = "total")
    private double total;
}
