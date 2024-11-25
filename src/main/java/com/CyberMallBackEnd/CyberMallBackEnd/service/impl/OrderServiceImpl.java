package com.CyberMallBackEnd.CyberMallBackEnd.service.impl;


import com.CyberMallBackEnd.CyberMallBackEnd.dto.OrderDto;
import com.CyberMallBackEnd.CyberMallBackEnd.entity.Order;
import com.CyberMallBackEnd.CyberMallBackEnd.repository.OrderRepository;
import com.CyberMallBackEnd.CyberMallBackEnd.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper mapper;


    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders=new ArrayList<>();
        orderRepository.findAll().forEach(order -> orders.add(mapper.map(order, OrderDto.class)));
        return orders;
    }

    public OrderDto searchOrderById(Long orderId) {
        return mapper.map(orderRepository.findById(orderId),OrderDto.class);
    }

    public Order saveOrder(OrderDto orderDto){
        if(orderDto !=null){
            orderDto.setDateTime(getCurrentDateTime());
            return orderRepository.save(mapper.map(orderDto, Order.class));
        }
        return null;
    }
    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public boolean updateOrder(Long id,OrderDto orderDto){

        if(orderDto!=null){
            Order order=mapper.map(orderDto,Order.class);
            order.setOrderId(id);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public boolean deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        return true;
    }
}