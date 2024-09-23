package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {
    HashMap<String, Object> datos;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public ResponseEntity<Object> addClient(Client client) {
        Optional<Client> res = clientRepository.findById(client.getId());
        datos = new HashMap<>();


        if (res.isPresent() && client.getId() == null) {
            datos.put("error", true);
            datos.put("message", "Ya existe un client con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardadó con éxito");
        if (client.getId() != null) {
            datos.put("message", "Se actualizó con éxito");
        }
        clientRepository.save(client);
        datos.put("data", client);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> getClient(String id) {
        Map<String, Object> datos = new HashMap<>();

        if (id == null || id.isEmpty()) {
            datos.put("error", true);
            datos.put("message", "El ID del client no puede estar vacío");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        boolean existe = clientRepository.existsById(id);
        if (!existe) {
            datos.put("error", true);
            datos.put("message", "No existe un client con ese ID");
        }

        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            datos.put("data", client.get());
            return new ResponseEntity<>(datos, HttpStatus.OK);
        } else {
            datos.put("error", true);
            datos.put("message", "Cliento no encontrado");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Object> updateClient(Client client) {
        Map<String, Object> datos = new HashMap<>();

        if (client.getId() == null) {
            datos.put("error", true);
            datos.put("message", "El ID del client no puede estar vacío");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        Optional<Client> existingClient = clientRepository.findById(client.getId());
        if (!existingClient.isPresent()) {
            datos.put("error", true);
            datos.put("message", "No existe un client con ese ID");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }

        Client updatedClient = clientRepository.save(client);
        datos.put("message", "Cliento actualizado con éxito");
        datos.put("data", updatedClient);

        return new ResponseEntity<>(datos, HttpStatus.OK);
    }


    public ResponseEntity<Object> deleteclient(String id) {
        datos = new HashMap<>();
        boolean existe = this.clientRepository.existsById(id);
        if (!existe) {
            datos.put("error", true);
            datos.put("message", "No existe un client con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        clientRepository.deleteById(id);
        datos.put("message", "client eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }


}