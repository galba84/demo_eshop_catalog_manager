package com.example.ishop.mappers;

import com.example.ishop.Entities.Product;
import com.example.ishop.dto.ProductDTO;
import com.example.ishop.models.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO mapModelToDto(ProductModel model) {
        ProductDTO dto = new ProductDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setPrice(model.getPrice());
        return dto;
    }

    public ProductModel mapDtoToModel(ProductDTO dto) {
        ProductModel model = new ProductModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setPrice(dto.getPrice());
        return model;
    }

    public Product mapDtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
