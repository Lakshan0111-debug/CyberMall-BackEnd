package com.CyberMallBackEnd.CyberMallBackEnd.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import com.CyberMallBackEnd.CyberMallBackEnd.dto.ProductDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Product;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.ProductRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


    public List<ProductDto> getAllProducts() {
        List<ProductDto> products=new ArrayList<>();
        for(Product product:productRepository.findAll()){
            if(product!=null){
                ProductDto productDto=mapper.map(product, ProductDto.class);
                try{
                    productDto.setImage(Base64.getEncoder().encodeToString(product.getImage()));

                    products.add(productDto);
                }catch (Exception e){
                    System.out.println(e);
                }

            }

        };

        return products;
    }


    public ProductDto searchProductById(Integer productId) {
        Product product=productRepository.findById(productId).get();
        ProductDto productDto=mapper.map(product,ProductDto.class);
        productDto.setImage(Base64.getEncoder().encodeToString(product.getImage()));

        return productDto;
    }

    public Product saveProduct(ProductDto productDto){
        if(productDto !=null){
            byte[] link=Base64.getDecoder().decode(productDto.getImage());
            Product product=mapper.map(productDto, Product.class);
            System.out.println(product.getProductId());
            product.setImage(link);
            return productRepository.save(product);
        }
        return null;
    }


    public boolean updateProduct(ProductDto productDto){

        if(productDto!=null){
            Product product=mapper.map(productDto,Product.class);
            product.setProductId(product.getProductId());
            productRepository.save(product);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return true;
    }

}