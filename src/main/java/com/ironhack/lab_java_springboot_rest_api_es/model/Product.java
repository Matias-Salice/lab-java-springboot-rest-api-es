package com.ironhack.lab_java_springboot_rest_api_es.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message = "The name is required")
    @Size(min = 3, message = "The name must have at least 3 characters")
    private String name;

    @Min(value = 0, message = "The price must be greater than 0")
    private int price;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value= 0, message = "The minimum number of units must be 0")
    private int quantity;

    public Product() {}

    public Product(String nombre, String category, int price, int quantity) {
        this.category = category;
        this.price = price;
        this.name = nombre;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
