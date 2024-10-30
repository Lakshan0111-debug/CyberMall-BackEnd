package com.CyberMallBackEnd.CyberMallBackEnd.service;

import java.util.ArrayList;
import java.util.List;


import com.CyberMallBackEnd.CyberMallBackEnd.dto.ProductDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Product;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    public List<ProductDto> getAllProducts() {
        List<ProductDto> products=new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(mapper.map(product, ProductDto.class)));
        return products;
    }


    public ProductDto searchProductById(Long productId) {
        return mapper.map(productRepository.findById(productId),ProductDto.class);
    }

    public Product saveProduct(ProductDto productDto){
        if(productDto !=null){

            return productRepository.save(mapper.map(productDto, Product.class));
        }
        return null;
    }


    public boolean updateProduct(Long id,ProductDto productDto){

        if(productDto!=null){
            Product product=mapper.map(productDto,Product.class);
            product.setProductId(id);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return true;
    }

}