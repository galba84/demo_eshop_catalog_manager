package com.example.ishop.controllers;

import com.example.ishop.Entities.Product;
import com.example.ishop.dto.ProductDTO;
import com.example.ishop.messages.InfoMessage;
import com.example.ishop.exceptions.DemoException;
import com.example.ishop.mappers.ProductMapper;
import com.example.ishop.models.ProductModel;
import com.example.ishop.services.MainService;
import com.example.ishop.validators.RequestValidator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Controller
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private RequestValidator requestValidator;
    @Autowired
    private ProductMapper productMapper;

    public MainController() {
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productModel", new ProductModel());
        List<Product> products = mainService.getAll();
        if (products == null) {
            products = Collections.singletonList(new Product());
        }
        model.addAttribute("products", products);
        return "index/list";
    }

    //products
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("productModel", new ProductModel());
        List<Product> products = mainService.getAll();
        if (products == null) {
            products = Collections.singletonList(new Product());
        }
        model.addAttribute("products", products);
        return "index/list";
    }

    //product
    @GetMapping("/product")
    public String searchProduct(@ModelAttribute ProductModel productModel, Model model) {
        try {
            ProductDTO productDTO = productMapper.mapModelToDto(productModel);
            requestValidator.validateSearchProduct(productDTO);
            Optional<Product> product = mainService.searchById(productDTO.getId());
            if (product.isPresent()) {
                model.addAttribute("products", Collections.singletonList(product.get()));
                return "index/result";
            } else {
                if (!productDTO.getName().isEmpty()) {
                    product = mainService.searchByName(productDTO.getName());
                    if (product.isPresent()) {
                        model.addAttribute("products", Collections.singletonList(product.get()));
                        return "index/result";
                    }
                }
                model.addAttribute("message", new InfoMessage("400", String.format("Product with is id %s not found", productDTO.getId())));
            }
        } catch (DemoException e) {
            model.addAttribute("message", new InfoMessage("400", e.getMessage()));
        } catch (Exception e) {
            model.addAttribute("message", new InfoMessage("500", "Internal Server Error!"));
        }
        return "index/messagePage";
    }

    @PostMapping("/product")
    public String addProduct(@ModelAttribute ProductModel productModel, Model model) {
        try {
            ProductDTO productDTO = productMapper.mapModelToDto(productModel);
            requestValidator.validateAddProduct(productDTO);
            ProductDTO savedProduct = mainService.add(productDTO);
            model.addAttribute("products", Collections.singletonList(savedProduct));
            return "index/result";
        } catch (DemoException e) {
            model.addAttribute("message", new InfoMessage("400", e.getMessage()));
        } catch (Exception e) {
            model.addAttribute("message", new InfoMessage("500", "Internal Server Error!"));
        }
        return "index/messagePage";
    }

    @PatchMapping("/product")
    public String updateProduct(@ModelAttribute ProductModel productModel, Model model) {
        try {
            ProductDTO productDTO = productMapper.mapModelToDto(productModel);
            requestValidator.validateUpdateProduct(productDTO);
            ProductDTO updatedProduct = mainService.update(productDTO);
            model.addAttribute("message", new InfoMessage("200", String.format("Product with id %s was patched", productDTO.getId())));
        } catch (DemoException e) {
            model.addAttribute("message", new InfoMessage("400", e.getMessage()));
        } catch (Exception e) {
            model.addAttribute("message", new InfoMessage("500", "Internal Server Error!"));
        }
        return "index/messagePage";
    }

    @DeleteMapping("/product")
    public String removeProduct(@ModelAttribute ProductModel productModel, Model model) {
        try {
            ProductDTO productDTO = productMapper.mapModelToDto(productModel);
            requestValidator.validateDeleteProduct(productDTO);
            mainService.delete(productDTO.getId());
            model.addAttribute("message", new InfoMessage("200", String.format("Product with id %s was deleted", productDTO.getId())));
        } catch (DemoException e) {
            model.addAttribute("message", new InfoMessage("400", e.getMessage()));
        } catch (Exception e) {
            model.addAttribute("message", new InfoMessage("500", "Internal Server Error!"));
        }
        return "index/messagePage";
    }
}