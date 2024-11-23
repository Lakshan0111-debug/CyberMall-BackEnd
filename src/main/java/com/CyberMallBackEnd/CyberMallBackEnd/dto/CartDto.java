package com.CyberMallBackEnd.CyberMallBackEnd.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDto {
    private int customerId; // Keep customer ID at the root
    private int cartId;
    private List<ProductDetail> productDetails; // Fixed variable name to follow Java conventions

    @Data
    public static class ProductDetail {
        private Long productId;
        private String title;
        private BigDecimal price; // Use BigDecimal for monetary values
        private int quantity;
        private BigDecimal total; // Use BigDecimal
    }
}
