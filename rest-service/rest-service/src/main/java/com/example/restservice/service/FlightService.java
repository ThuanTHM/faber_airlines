package com.example.restservice.service;

import com.example.restservice.entity.Flight;
import com.example.restservice.exception.RecordNotFoundException;
import com.example.restservice.viewmodel.FlightForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface FlightService {
    Collection<Flight> findAll();
    Page<Flight> findAll(PageRequest p);
    Flight create(Flight flight);
    Flight create(FlightForm flightForm);
    Flight save(Flight flight);
    void delete(Long id) throws RecordNotFoundException;
    Collection<Flight> filtering(Flight flight);
    Flight findById(Long id) throws RecordNotFoundException;
}
