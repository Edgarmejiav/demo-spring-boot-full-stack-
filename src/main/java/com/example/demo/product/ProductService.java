package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    HashMap<String, Object> datos;
    private final ProducRepository producRepository;

    @Autowired
    public ProductService(ProducRepository producRepository) {
        this.producRepository = producRepository;
    }

    public List<Product> getProducts() {
        return producRepository.findAll();
    }

    public ResponseEntity<Object> addProduct(Product product) {
        Optional<Product> res = producRepository.findById(product.getId());
        datos = new HashMap<>();


        if (res.isPresent() && product.getId() == null) {
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardadó con éxito");
        if (product.getId() != null) {
            datos.put("message", "Se actualizó con éxito");
        }
        producRepository.save(product);
        datos.put("data", product);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> getProduct(String id) {
        Map<String, Object> datos = new HashMap<>();

        if (id == null || id.isEmpty()) {
            datos.put("error", true);
            datos.put("message", "El ID del producto no puede estar vacío");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        boolean existe = producRepository.existsById(id);
        if (!existe) {
            datos.put("error", true);
            datos.put("message", "No existe un producto con ese ID");
        }

        Optional<Product> product = producRepository.findById(id);
        if (product.isPresent()) {
            datos.put("data", product.get());
            return new ResponseEntity<>(datos, HttpStatus.OK);
        } else {
            datos.put("error", true);
            datos.put("message", "Producto no encontrado");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Object> updateProduct(Product product) {
        Map<String, Object> datos = new HashMap<>();

        // Verificar que el ID del producto no sea nulo
        if (product.getId() == null) {
            datos.put("error", true);
            datos.put("message", "El ID del producto no puede estar vacío");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        // Verificar si el producto existe
        Optional<Product> existingProduct = producRepository.findById(product.getId());
        if (!existingProduct.isPresent()) {
            datos.put("error", true);
            datos.put("message", "No existe un producto con ese ID");
            return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
        }

        // Actualizar el producto
        Product updatedProduct = producRepository.save(product);
        datos.put("message", "Producto actualizado con éxito");
        datos.put("data", updatedProduct);

        return new ResponseEntity<>(datos, HttpStatus.OK);
    }


    public ResponseEntity<Object> deleteproduct(String id) {
        datos = new HashMap<>();
        boolean existe = this.producRepository.existsById(id);
        if (!existe) {
            datos.put("error", true);
            datos.put("message", "No existe un producto con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        producRepository.deleteById(id);
        datos.put("message", "producto eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }



}