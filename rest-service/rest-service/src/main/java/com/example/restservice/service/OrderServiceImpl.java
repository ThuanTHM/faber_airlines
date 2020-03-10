package com.example.restservice.service;

import com.example.restservice.entity.Order;
import com.example.restservice.jpa.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.logging.Logger;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
    static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Order order) {
        return null;
    }

    @Override
    public Collection<Order> filtering(Order order) {
        return null;
    }

    public Collection<Order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        return null;
    }

}
