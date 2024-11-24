package com.CyberMallBackEnd.CyberMallBackEnd.service;

import com.CyberMallBackEnd.CyberMallBackEnd.dto.OrderDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto searchOrderById(Long orderId);
    Order saveOrder(OrderDto orderDto);
    boolean updateOrder(Long id,OrderDto orderDto);
    boolean deleteOrder(Long orderId);
}
