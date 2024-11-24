package com.CyberMallBackEnd.CyberMallBackEnd.service;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.ProductDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto searchProductById(Integer productId);
    Product saveProduct(ProductDto productDto);
    boolean updateProduct(ProductDto productDto);
    boolean deleteProduct(Integer productId);
}
