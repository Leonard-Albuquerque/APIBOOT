package com.example.APIBOOT.mapper;

import com.example.APIBOOT.dto.ProductDTO;
import org.mapstruct.factory.Mappers;
import com.example.APIBOOT.model.Product;
import org.mapstruct.Mapper;


@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO todto(Product product);
    Product toEntity(ProductDTO dto);
}
