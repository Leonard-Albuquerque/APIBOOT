package com.example.APIBOOT.controller;

import com.example.APIBOOT.dto.ProductDTO;
import com.example.APIBOOT.model.Product;
import com.example.APIBOOT.repository.ProductRepository;
import com.example.APIBOOT.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
