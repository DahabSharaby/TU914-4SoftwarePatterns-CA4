package com.example.ca4shop;

public class Product {
    private String name;
    private String manufacturer;
    private String price;
    private String category;
    private String imageURL;

    public Product() {
        // Default constructor required for Firestore
    }

    public Product(String name, String manufacturer, String price, String category, String imageURL) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.category = category;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getImageURL() {
        return imageURL;
    }
}
