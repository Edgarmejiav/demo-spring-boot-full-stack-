package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")

public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    @GetMapping("/getProduct")
    public ResponseEntity<Object> getProduct(@RequestParam String id) {
        return productService.getProduct(id);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> eliminar( @PathVariable("productId") String id){
        return this.productService.deleteproduct(id);
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);

    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }


}
