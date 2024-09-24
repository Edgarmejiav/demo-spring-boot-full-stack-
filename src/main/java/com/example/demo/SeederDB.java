package com.example.demo;

import com.example.demo.model.Client;
import com.example.demo.model.Product;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SeederDB implements CommandLineRunner {


    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;



    public SeederDB(ProductRepository productRepository, ClientRepository clientRepository) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Sembrar productos
        List<Product> products = Arrays.asList(
                new Product("iPhone 11", "This is the iPhone 11, featuring a dual-camera system, A13 Bionic chip, and all-day battery life.", 200),
                new Product("iPhone 12", "This is the iPhone 12, featuring a Super Retina XDR display, 5G support, and Ceramic Shield for better durability.", 300),
                new Product("iPhone 13", "This is the iPhone 13, featuring an A15 Bionic chip, improved dual-camera system, and longer battery life.", 400),
                new Product("iPhone 14", "This is the iPhone 14, with an advanced dual-camera system, faster performance, and improved durability.", 500),
                new Product("iPhone 15", "This is the iPhone 15, featuring Dynamic Island, advanced camera capabilities, and USB-C charging.", 600),
                new Product("iPhone 16", "This is the iPhone 16, with a next-generation A17 chip, ProMotion display, and all-day battery life.", 700)
        );

        productRepository.saveAll(products);
        System.out.println("Datos de productos ingresados correctamente.");

        // Sembrar clientes
        List<Client> clients = Arrays.asList(
                new Client("Alice Johnson", "alice.johnson@example.com"),
                new Client("Bob Smith", "bob.smith@example.com"),
                new Client("Charlie Brown", "charlie.brown@example.com"),
                new Client("Diana Prince", "diana.prince@example.com"),
                new Client("Edward Elric", "edward.elric@example.com")
        );

        clientRepository.saveAll(clients);
        System.out.println("Datos de clientes ingresados correctamente.");
    }
}