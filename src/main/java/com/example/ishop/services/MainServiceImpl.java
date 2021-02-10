package com.example.ishop.services;

import com.example.ishop.Entities.Product;
import com.example.ishop.dto.ProductDTO;
import com.example.ishop.exceptions.DemoException;
import com.example.ishop.mappers.ProductMapper;
import com.example.ishop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAll() {
        List<Product> productsEntities = productRepository.findAll();
        return productsEntities.stream().map(productMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO add(final ProductDTO productDTO) throws IOException {
        Product product = productMapper.mapDtoToEntity(productDTO);
        Product dbProduct = productRepository.save(product);
        return productMapper.mapEntityToDto(dbProduct);
    }

    @Override
    public Optional<ProductDTO> searchById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::mapEntityToDto);
    }

    @Override
    public Optional<Product> searchByName(String name) {
        Example<Product> example = Example.of(new Product(name));
        return productRepository.findOne(example);
    }

    @Override
    public void update(ProductDTO productDTO) throws DemoException, IOException {
        Optional<Product> existingProduct = productRepository.findById(productDTO.getId());
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            if (!productDTO.getName().isEmpty()) {
                product.setName(productDTO.getName());
            }
            if (!productDTO.getPrice().isEmpty()) {
                product.setPrice(productDTO.getPrice());
            }
            if (!productDTO.getDescription().isEmpty()) {
                product.setDescription(productDTO.getDescription());
            }
            if (productDTO.getImage() != null) {
                product.setImage(
                        productDTO.getImage());
            }
            productRepository.save(product);
        } else {
            throw new DemoException(String.format("Coulnd not find a product with id %s", productDTO.getId()));
        }
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
