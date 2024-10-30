package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.ProductDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Product;
import com.CyberMallBackEnd.CyberMallBackEnd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/search-product/{productId}")
    public ProductDto searchProductById(@PathVariable Long productId){
        return productService.searchProductById(productId);
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> saveProduct(@RequestBody @RequestParam("image") MultipartFile file,
                                              @RequestParam("productName") String productName,
                                              @RequestParam("description") String description,
                                              @RequestParam("supplierName") String supplierName,
                                              @RequestParam("unitPrice") String unitPrice,
                                              @RequestParam("quantity") String quantity) {

        try {
            productService.saveProduct(new ProductDto(productName, description, supplierName, unitPrice, quantity,file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Product saved successfully!");
    }

    // PUT method for updating an existing product
    @PutMapping("/update-product/{productId}")
    public ResponseEntity<String> updateProduct(@RequestBody @PathVariable Long productId,
                                                @RequestParam("image") MultipartFile file,
                                                @RequestParam("productName") String productName,
                                                @RequestParam("description") String description,
                                                @RequestParam("supplierName") String supplierName,
                                                @RequestParam("unitPrice") String unitPrice,
                                                @RequestParam("quantity") String quantity) {
        boolean isUpdated = false;
        try {
            isUpdated = productService.updateProduct(productId,new ProductDto(productName, description, supplierName, unitPrice, quantity, file.getBytes()));
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
    public boolean deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }
}
