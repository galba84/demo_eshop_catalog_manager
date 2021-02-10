package com.example.ishop.services;

import com.example.ishop.Entities.Product;
import com.example.ishop.dto.ProductDTO;
import com.example.ishop.exceptions.DemoException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MainService {
    List<ProductDTO> getAll();
    ProductDTO add(ProductDTO productDTO) throws IOException;
    Optional<ProductDTO> searchById(String id);
    Optional<Product> searchByName(String name);
    void update(ProductDTO productDTO) throws DemoException, IOException;
    void delete(String id);
}
