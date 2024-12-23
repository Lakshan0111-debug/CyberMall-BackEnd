package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.CartDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Cart;
import com.CyberMallBackEnd.CyberMallBackEnd.service.impl.CartServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartServiceIml cartServiceIml;

    @PostMapping("/createCart")
    public ResponseEntity<String> addCart(@RequestBody CartDto cartDto) {
        try {
            String response = cartServiceIml.addCart(cartDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCart/{CartId}")
    public ResponseEntity<Cart> getCart(@PathVariable String CartId) {
        try {
            int id = Integer.parseInt(CartId);
            Cart Cart = cartServiceIml.getCartDetailsByID(id);
            return ResponseEntity.ok(Cart);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null); // Return bad request if CartId is not a valid integer
        }
    }

}
