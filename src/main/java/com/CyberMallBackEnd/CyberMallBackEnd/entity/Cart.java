package com.CyberMallBackEnd.CyberMallBackEnd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    private int cartId;

    private LocalDate date;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Fixed column name
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> billItems = new ArrayList<>(); // Initialize list

    public void addCartItem(Product product, int quantity, BigDecimal subtotal) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setSubtotal(subtotal);
        cartItem.setCart(this); // Set the parent reference
        this.billItems.add(cartItem);
    }
}
