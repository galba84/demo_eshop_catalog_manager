package com.example.ishop.services;

import com.example.ishop.Entities.Product;
import com.example.ishop.dto.ProductDTO;
import com.example.ishop.exceptions.DemoException;
import com.example.ishop.mappers.ProductMapper;
import com.example.ishop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDTO add(final ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        Product dbProduct = productRepository.save(product);
        ProductDTO savedProduct = new ProductDTO();
        savedProduct.setId(dbProduct.getId());
        savedProduct.setName(dbProduct.getName());
        savedProduct.setPrice(dbProduct.getPrice());
        return savedProduct;
    }

    @Override
    public Optional<Product> searchById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> searchByName(String name) {
        Example<Product> example = Example.of(new Product(name));
        return productRepository.findOne(example);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) throws DemoException {
        Optional<Product> existingProduct = productRepository.findById(productDTO.getId());
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            if (!productDTO.getName().isEmpty()) {
                product.setName(productDTO.getName());
            }
            if (!productDTO.getPrice().isEmpty()) {
                product.setPrice(productDTO.getPrice());
            }
            Product dbProduct = productRepository.save(product);
            ProductDTO updatedProduct = new ProductDTO();
            updatedProduct.setId(dbProduct.getId());
            updatedProduct.setName(dbProduct.getName());
            updatedProduct.setPrice(dbProduct.getPrice());
            return updatedProduct;
        } else {
            throw new DemoException(String.format("Coulnd not find a product with id %s", productDTO.getId()));
        }
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
