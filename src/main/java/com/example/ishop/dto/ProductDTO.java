package com.example.ishop.dto;

import lombok.Data;
import org.bson.types.Binary;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String price;
    private String description;
    private Binary image;
    private String imageType;
    private String imageName;
}
