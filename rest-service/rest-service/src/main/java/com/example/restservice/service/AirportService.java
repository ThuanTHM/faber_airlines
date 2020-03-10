package com.example.restservice.service;

import com.example.restservice.entity.Airport;
import com.example.restservice.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface AirportService {
    Collection<Airport> findAll();
    Page<Airport> findAll(PageRequest p);
    Airport findById(Long id) throws RecordNotFoundException;
    Airport createOrUpdate(Airport airport);
    Airport save(Airport airport);
    void delete(Long id) throws RecordNotFoundException;
    Collection<Airport> filtering(Airport airport);
}
