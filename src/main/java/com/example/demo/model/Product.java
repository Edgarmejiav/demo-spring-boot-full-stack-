package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table
public class Product {
    @Id
    private String id; // ID único basado en UUID
    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.id = UUID.randomUUID().toString(); // Genera un UUID único
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {
        this.id = UUID.randomUUID().toString(); // Genera un UUID único en el constructor vacío
    }
}
