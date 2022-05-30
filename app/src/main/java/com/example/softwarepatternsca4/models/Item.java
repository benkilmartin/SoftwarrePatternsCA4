package com.example.softwarepatternsca4.models;

import androidx.recyclerview.widget.RecyclerView;

public class Item {
    String title;
    String category;
    double price;

    int stockLevel;
    String id;

    public Item(){

    }

    public Item(String title, String category, double price, int stockLevel, String id) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.stockLevel = stockLevel;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
