package com.example.ishop.mappers;

import com.example.ishop.Entities.Product;
import com.example.ishop.dto.ProductDTO;
import com.example.ishop.requestObjects.ProductRequest;
import com.example.ishop.responseObjects.ProductResponse;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Component
public class ProductMapper {
    public ProductDTO mapRequestToDto(ProductRequest model) throws IOException {
        ProductDTO dto = new ProductDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setPrice(model.getPrice());
        dto.setDescription(model.getDescription());
        if (model.getImage() != null) {
            dto.setImage(new Binary(BsonBinarySubType.BINARY, model.getImage().getBytes()));
        }
        return dto;
    }

    public Product mapDtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        return product;
    }

    public ProductDTO mapEntityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        return productDTO;
    }

    public ProductResponse mapDtoToResponse(ProductDTO productDTO) {
        ProductResponse response = new ProductResponse();
        response.setId(productDTO.getId());
        response.setName(productDTO.getName());
        response.setDescription(productDTO.getDescription());
        response.setPrice(productDTO.getPrice());
        if (productDTO.getImage() != null) {
            response.setImage(Base64.getEncoder().encodeToString(productDTO.getImage().getData()));
        }
        return response;
    }
}
