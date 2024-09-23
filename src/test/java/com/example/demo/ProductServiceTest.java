package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product("123", "Product Name", 100.0); // Asegúrate de que el constructor sea correcto
    }


    @Test
    void testGetProducts() {
        Product product1 = new Product("123", "Product 1", 100.0);
        Product product2 = new Product("456", "Product 2", 200.0);
        List<Product> expectedProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(expectedProducts);
        List<Product> actualProducts = productService.getProducts();
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void testDeleteProduct_ProductNotFound() {
        String productId = "123";
        when(productRepository.existsById(productId)).thenReturn(false);

        ResponseEntity<Object> response = productService.deleteproduct(productId);

        assertEquals(409, response.getStatusCodeValue()); // HTTP CONFLICT
        assertEquals("No existe un producto con ese id", ((Map<String, Object>) response.getBody()).get("message"));
        verify(productRepository).existsById(productId);
        verify(productRepository, never()).deleteById(productId);
    }

    @Test
    void testDeleteProduct_Success() {
        String productId = "123";
        when(productRepository.existsById(productId)).thenReturn(true);

        ResponseEntity<Object> response = productService.deleteproduct(productId);

        assertEquals(202, response.getStatusCodeValue()); // HTTP ACCEPTED
        assertEquals("producto eliminado", ((Map<String, Object>) response.getBody()).get("message"));
        verify(productRepository).existsById(productId);
        verify(productRepository).deleteById(productId);
    }


}
