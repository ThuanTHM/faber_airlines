package com.example.restservice.service;

import com.example.restservice.entity.Seat;
import com.example.restservice.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.Date;
import org.springframework.transaction.UnexpectedRollbackException;

public interface SeatService {
    Collection<Seat> findAll();
    Page<Seat> findAll(PageRequest p);
    Seat createOrUpdate(Seat seat);
    void delete(Long id) throws RecordNotFoundException;
    Seat findById(Long id) throws RecordNotFoundException;
    Collection<Seat> findSuitableDepartingSlots(String departureLocation, String arrivalLocation, Date departureDate) throws UnexpectedRollbackException;
    Collection<Seat> findSuitableReturningSlots(String departureLocation, String arrivalLocation, Date returningDate) throws UnexpectedRollbackException;
}
