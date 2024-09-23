package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/client")

public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/getClient")
    public ResponseEntity<Object> getClient(@RequestParam String id) {
        return clientService.getClient(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") String id) {
        return this.clientService.deleteclient(id);
    }

    @PostMapping
    public ResponseEntity<Object> addClient(@RequestBody Client product) {
        return clientService.addClient(product);

    }

    @PutMapping
    public ResponseEntity<Object> updateClient(@RequestBody Client product) {
        return clientService.updateClient(product);
    }


}
