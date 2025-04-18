package com.ironhack.lab_java_springboot_rest_api_es.controller;
import com.ironhack.lab_java_springboot_rest_api_es.exception.ProductException;

import com.ironhack.lab_java_springboot_rest_api_es.model.Product;
import com.ironhack.lab_java_springboot_rest_api_es.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void validateApiKey(String apiKey) {
        if (apiKey == null || !apiKey.equals(API_KEY)) {
            throw new ProductException.MissingApiKeyException();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@RequestHeader("API-Key") String apiKey,
                                                    @PathVariable String name) {
        validateApiKey(apiKey);
        Product product = productService.getProductByName(name);
        if (product == null) {
            throw new ProductException.ProductNotFoundException(name);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateProduct(@RequestHeader("API-Key") String apiKey,@PathVariable String name,@Valid @RequestBody Product updatedProduct) {
        validateApiKey(apiKey);
        boolean updated = productService.updateProduct(name, updatedProduct);
        if (!updated) {
            throw new ProductException.ProductNotFoundException(name);
        }
        return ResponseEntity.ok("Producto actualizado correctamente.");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(@RequestHeader("API-Key") String apiKey,
                                                @PathVariable String name) {
        validateApiKey(apiKey);
        boolean deleted = productService.deleteProduct(name);
        if (!deleted) {
            throw new ProductException.ProductNotFoundException(name);
        }
        return ResponseEntity.ok("Producto eliminado correctamente.");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestHeader("API-Key") String apiKey,
                                                               @PathVariable String category) {
        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestHeader("API-Key") String apiKey,
                                                                 @RequestParam int min,
                                                                 @RequestParam int max) {
        validateApiKey(apiKey);
        if (min < 0 || max < 0 || min > max) {
            throw new ProductException.InvalidPriceRangeException();
        }
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}
