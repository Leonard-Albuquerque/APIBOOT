package com.example.APIBOOT.service;
import com.example.APIBOOT.dto.ProductDTO;
import com.example.APIBOOT.mapper.ProductMapper;
import com.example.APIBOOT.model.Product;
import com.example.APIBOOT.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper.INSTANCE::todto)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.todto(product);
    }
}
