package com.example.restservice.service;

import com.example.restservice.entity.Order;

import java.util.Collection;

public interface OrderService {
    Collection<Order> findAll();
    Order create(Order airport);
    Order save(Order airport);
    Order delete(Order airport);
    Collection<Order> filtering(Order airport);
}
