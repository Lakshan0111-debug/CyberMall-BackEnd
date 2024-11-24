package com.CyberMallBackEnd.CyberMallBackEnd.service;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.CartDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Cart;

public interface CartService {
    String addCart(CartDto cartDto);
    Cart getCartDetailsByID(int cartId);

}
