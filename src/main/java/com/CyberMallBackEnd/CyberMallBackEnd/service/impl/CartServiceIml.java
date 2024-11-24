package com.CyberMallBackEnd.CyberMallBackEnd.service.impl;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.CartDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Cart;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.CartItem;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Customer;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Product;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.CartRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.CustomerRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.ProductRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceIml implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;



    @Transactional
    public String addCart(CartDto cartDto) {
        // Fetch the customer using customerId
        Customer customer = customerRepository.findById( cartDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Create a new Cart
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCartId());
        cart.setDate(LocalDate.now());
        cart.setCustomer(customer);

        BigDecimal total = BigDecimal.ZERO;

        // Iterate over product details
        for (CartDto.ProductDetail productDetail : cartDto.getProductDetails()) {
            // Fetch the product using productId
            Product product = productRepository.findById(productDetail.getProductId())
                    .orElseThrow(() -> new RuntimeException("Item not found!"));

            // Check if product has sufficient quantity
            int productQuantity = Integer.parseInt(product.getQuantity()); // Convert String to int
            if (productQuantity < productDetail.getQuantity()) {
                throw new RuntimeException("Insufficient quantity for item: " + product.getProductName());
            }

// Update product quantity
            product.setQuantity(String.valueOf(productQuantity - productDetail.getQuantity())); // Convert int to String if necessary
            productRepository.save(product);
            // Calculate subtotal
            BigDecimal subtotal = product.getUnitPrice().multiply(BigDecimal.valueOf(productDetail.getQuantity()));
            total = total.add(subtotal);

            // Add the product to the cart
            cart.addCartItem(product, productDetail.getQuantity(), subtotal);
        }

        // Set the total price of the cart
        cart.setTotalPrice(total);

        // Save the cart to the database
        cartRepository.save(cart);

        return "Cart created successfully with ID: " + cart.getCartId();
    }

    public boolean deleteItemFromCart(int cartId,int productId){
        Cart  cart=cartRepository.findById(cartId).get();
        List<CartItem> cartItems= cart.getCartItems();
        if(cartId>0 & cartItems.size()>=1){
            cartRepository.deleteById(productId);
            return true;
        }
        return false;
    }
    public Cart getCartDetailsByID(int cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
}
