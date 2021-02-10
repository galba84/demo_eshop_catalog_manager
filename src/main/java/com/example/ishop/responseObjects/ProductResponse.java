package com.example.ishop.responseObjects;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductResponse {
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
    private String description;
    private String image;
}
