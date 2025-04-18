package com.ironhack.lab_java_springboot_rest_api_es.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Customer {

    @NotBlank(message = "The name is required")
    private String name;

    @Email(message = "The email must be in a valid format.")
    private String email;

    @Min(value = 18, message = "The minimum age must be 18.")
    private int age;

    @NotBlank(message = "The address is required")
    private String address;


    public Customer() {}

    public Customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
