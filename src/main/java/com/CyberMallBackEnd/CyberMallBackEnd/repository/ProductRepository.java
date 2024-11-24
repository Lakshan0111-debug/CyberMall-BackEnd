package com.CyberMallBackEnd.CyberMallBackEnd.repository;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
