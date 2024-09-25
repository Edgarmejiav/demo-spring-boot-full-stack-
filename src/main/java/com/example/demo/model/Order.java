package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document(collection = "orders")
public class Order {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private  Client client;
    private  List<Product> products;
    private  Double total;

    public Order(Client client, List<Product> products) {
        this.id = new ObjectId().toString();
        this.client = client;
        this.products = products;
        this.total = products.stream().mapToDouble(Product::getPrice).sum(); // Calcula el total
    }

    public Order() {
        this.id = new ObjectId().toString();
        this.client = null;
        this.products = null;
        this.total = 0.0;
    }



}
