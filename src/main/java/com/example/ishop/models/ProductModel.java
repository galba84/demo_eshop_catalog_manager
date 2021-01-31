package com.example.ishop.models;

import lombok.Data;

@Data
public class ProductModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    private String id;
    private String name;
    private String price;
}
