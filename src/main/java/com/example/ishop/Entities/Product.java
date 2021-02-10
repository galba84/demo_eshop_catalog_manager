package com.example.ishop.Entities;

import com.mysema.query.annotations.QueryEntity;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@QueryEntity
@Document
@Data
public class Product {

    @Id
    private String id;
    @Version
    Long version;

    private String name;
    private String price;
    private String description;
    private Binary image;

    public Product() {
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', price='%s']",
                id, name, price);
    }

    public Product(String name) {
        this.name = name;
    }

}