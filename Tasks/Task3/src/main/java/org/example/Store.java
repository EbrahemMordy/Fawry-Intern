package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Store {
    private List<Product> products;

    public Store() {
        products = new ArrayList<>();
        try {
            products.add(new Product("alo", 10));
            products.add(new Product("hey", 10));
        } catch (Exception ignored) {
        }
    }

    public Product addProduct(String name, double price) throws Exception {
        Product product = new Product(name, price);
        products.add(product);
        return product;
    }

    public void deleteProduct(String name) throws Exception {
        long count = products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).count();
        if (count > 0) {
            Optional<Product> p = products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).findFirst();
            products.remove(p.get());
        } else {
            throw new Exception("delete failed Not Found");
        }
    }

    public Product updateProduct(String name, String newName, double newPrice) throws Exception {
        long count = products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).count();
        if (count > 0) {
            deleteProduct(name);
            return addProduct(newName, newPrice);
        } else {
            throw new Exception("update failed Not Found!!~");
        }
    }

    public Optional<Product> getProduct(String name) throws Exception {
        long count = products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).count();
        if (count > 0) {
            return products.stream().filter(product -> product.getName().equalsIgnoreCase(name)).findFirst();
        } else {
            throw new Exception("Get failed Not Found");
        }
    }
}

