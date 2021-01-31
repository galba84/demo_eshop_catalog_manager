package com.example.ishop.services;

import com.example.ishop.Entities.Product;
import com.example.ishop.dto.ProductDTO;
import com.example.ishop.exceptions.DemoException;

import java.util.List;
import java.util.Optional;

public interface MainService {
    List<Product> getAll();
    ProductDTO add(ProductDTO productDTO);
    Optional<Product> searchById(String id);
    Optional<Product> searchByName(String name);
    ProductDTO update(ProductDTO productDTO) throws DemoException;
    void delete(String id);
}
