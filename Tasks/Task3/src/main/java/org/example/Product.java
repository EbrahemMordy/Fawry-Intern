package org.example;

public class Product {
    public static int createdProducts = 1;
    private int id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(String name, double price) throws Exception {
        this.setId(createdProducts);
        createdProducts++;
        this.setName(name);
        this.setPrice(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name Must be less than 100 char");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws Exception {
        if (price < 0) {
            throw new IllegalArgumentException("Price Must be NonNegative");
        }
        this.price = price;
    }

    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
