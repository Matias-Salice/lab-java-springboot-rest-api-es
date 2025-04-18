package com.ironhack.lab_java_springboot_rest_api_es.controller;

import com.ironhack.lab_java_springboot_rest_api_es.exception.CustomerException;
import com.ironhack.lab_java_springboot_rest_api_es.model.Customer;
import com.ironhack.lab_java_springboot_rest_api_es.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private static final String API_KEY = "123456";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private void validateApiKey(String apiKey) {
        if (apiKey == null || !apiKey.equals(API_KEY)) {
            throw new CustomerException.MissingApiKeyException();
        }
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestHeader("API-Key") String apiKey, @Valid @RequestBody Customer customer) {
        validateApiKey(apiKey);
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Customer created.");
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestHeader("API-Key") String apiKey, @PathVariable String email) {
        validateApiKey(apiKey);
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            throw new CustomerException.EmailNotFoundException(email);
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@RequestHeader("API-Key") String apiKey, @PathVariable String email, @Valid @RequestBody Customer updatedCustomer) {
        validateApiKey(apiKey);
        boolean updated = customerService.updateCustomer(email, updatedCustomer);
        if (!updated) {
            throw new CustomerException.EmailNotFoundException(email);
        }
        return ResponseEntity.ok("Customer updated.");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader("API-Key") String apiKey, @PathVariable String email) {
        validateApiKey(apiKey);
        boolean deleted = customerService.deleteCustomer(email);
        if (!deleted) {
            throw new CustomerException.EmailNotFoundException(email);
        }
        return ResponseEntity.ok("Customer deleted.");
    }
}
