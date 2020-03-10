package com.example.restservice.service;

import com.example.restservice.entity.Seat;
import com.example.restservice.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface SeatService {
    Collection<Seat> findAll();
    Page<Seat> findAll(PageRequest p);
    Seat createOrUpdate(Seat seat);
    void delete(Long id) throws RecordNotFoundException;
    Seat findById(Long id) throws RecordNotFoundException;
}
