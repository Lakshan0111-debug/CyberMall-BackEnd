package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.ProductDto;
import com.CyberMallBackEnd.CyberMallBackEnd.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/get-all")
    public List<ProductDto> getAllProducts() {
        return productServiceImpl.getAllProducts();
    }


    @GetMapping("/search-product/{productId}")
    public ProductDto searchProductById(@PathVariable Integer productId){
        return productServiceImpl.searchProductById(productId);
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> saveProduct(@RequestBody @RequestParam("image") MultipartFile file,        @RequestParam("productId") Integer productId,
                                              @RequestParam("productName") String productName,
                                              @RequestParam("description") String description,
                                              @RequestParam("supplierName") String supplierName,
                                              @RequestParam("unitPrice") String unitPrice,
                                              @RequestParam("quantity") String quantity
                                              ) {



        try {
            System.out.println(file);
            productServiceImpl.saveProduct(new ProductDto(productId,productName, description, supplierName, unitPrice, quantity, Base64.getEncoder().encodeToString(file.getBytes())));
          //  productServiceImpl.saveProduct(new ProductDto(0, productName, description, supplierName, unitPrice, quantity, file.getBytes().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Product saved successfully!");
    }

    // PUT method for updating an existing product
    @PutMapping("/update-product/{productId}")
    public ResponseEntity<String> updateProduct(@RequestBody @PathVariable Integer productId,
                                                @RequestParam("image") MultipartFile file,
                                                @RequestParam("productName") String productName,
                                                @RequestParam("description") String description,
                                                @RequestParam("supplierName") String supplierName,
                                                @RequestParam("unitPrice") String unitPrice,
                                                @RequestParam("quantity") String quantity) {
        boolean isUpdated = false;
        try {
            isUpdated = productServiceImpl.updateProduct(new ProductDto(productId,productName, description, supplierName, unitPrice, quantity, Base64.getEncoder().encodeToString(file.getBytes())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (isUpdated) {
            return ResponseEntity.ok("Product updated successfully!");
        } else {
            return ResponseEntity.notFound().build(); // If product with the given ID is not found
        }
    }

    @DeleteMapping("delete-product/{productId}")
    public boolean deleteProduct(@PathVariable Integer productId){
        return productServiceImpl.deleteProduct(productId);
    }
}
