package com.example.dante.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable{
    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private double price;

    public MenuItem(String name, String description, String imageUrl, String category, double price){
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }
}
