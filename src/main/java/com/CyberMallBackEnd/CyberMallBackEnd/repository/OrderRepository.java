package com.CyberMallBackEnd.CyberMallBackEnd.repository;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
