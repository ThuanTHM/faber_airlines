package com.example.restservice.service;

import com.example.restservice.entity.Order;
import com.example.restservice.exception.RecordNotFoundException;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface OrderService {
    Collection<Order> findAll();
    Page<Order> findAll(PageRequest p);
    Order createOrUpdate(Order order);
}
