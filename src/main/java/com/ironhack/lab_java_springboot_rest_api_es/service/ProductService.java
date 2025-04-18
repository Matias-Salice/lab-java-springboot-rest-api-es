package com.ironhack.lab_java_springboot_rest_api_es.service;

import com.ironhack.lab_java_springboot_rest_api_es.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ArrayList<Product> productArrayList = new ArrayList<>();

    public ProductService(){
        productArrayList.add(new Product("Computer", "Electronics", 20, 2));
        productArrayList.add(new Product("Phone", "Electronics", 15, 5));
        productArrayList.add(new Product("Ergonomic Chair", "Furniture", 10, 3));
        productArrayList.add(new Product("27-inch Monitor", "Electronics", 12, 4));
        productArrayList.add(new Product("Wooden Desk", "Furniture", 5, 1));
        productArrayList.add(new Product("Thermal Bottle", "Accessories", 30, 6));
        productArrayList.add(new Product("Wireless Headphones", "Electronics", 18, 3));
        productArrayList.add(new Product("Laptop Backpack", "Accessories", 22, 8));
        productArrayList.add(new Product("LED Lamp", "Home", 40, 10));

    }

    public List<Product> getAllProducts() {
        return productArrayList;
    }

    public void addProduct(Product product) {
        productArrayList.add(product);
    }

    public Product getProductByName(String name) {
        for (Product product : productArrayList) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public boolean updateProduct(String name, Product updatedProduct) {
        for (Product product : productArrayList) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String name) {
        for (int i = 0; i < productArrayList.size(); i++) {
            if (productArrayList.get(i).getName().equalsIgnoreCase(name)) {
                productArrayList.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> filter = new ArrayList<>();
        for (Product product : productArrayList) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filter.add(product);
            }
        }
        return filter;
    }

    public List<Product> getProductsByPriceRange(int minPrice, int maxPrice) {
        List<Product> filter = new ArrayList<>();
        for (Product product : productArrayList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filter.add(product);
            }
        }
        return filter;
    }


}
