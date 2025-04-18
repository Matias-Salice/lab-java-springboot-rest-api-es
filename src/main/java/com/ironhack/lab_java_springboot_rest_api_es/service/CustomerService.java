package com.ironhack.lab_java_springboot_rest_api_es.service;
import com.ironhack.lab_java_springboot_rest_api_es.model.Customer;
import com.ironhack.lab_java_springboot_rest_api_es.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customerList = new ArrayList<>();

    public CustomerService() {
        customerList.add(new Customer("Juan Pérez", "juan.perez@gmail.com", 30, "Av. Siempre Viva 123"));
        customerList.add(new Customer("María Gómez", "maria.gomez@gmail.com", 25, "Calle Falsa 456"));
        customerList.add(new Customer("Carlos Díaz", "carlos.diaz@gmail.com", 42, "Ruta 9 Km 20"));
        customerList.add(new Customer("Laura Fernández", "laura.fernandez@gmail.com", 19, "San Martín 789"));
        customerList.add(new Customer("Ana López", "ana.lopez@gmail.com", 22, "Belgrano 321"));
    }


    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        return null;
    }

    public boolean updateCustomer(String email, Customer updatedCustomer) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                customer.setAddress(updatedCustomer.getAddress());
                customer.setName(updatedCustomer.getName());
                customer.setAge(updatedCustomer.getAge());
                return true;
            }
        }
        return false;
    }

    public boolean deleteCustomer(String email) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getEmail().equalsIgnoreCase(email)) {
                customerList.remove(i);
                return true;
            }
        }
        return false;
    }
}
