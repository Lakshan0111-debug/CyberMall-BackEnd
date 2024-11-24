package com.CyberMallBackEnd.CyberMallBackEnd.repository;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>  {
}
