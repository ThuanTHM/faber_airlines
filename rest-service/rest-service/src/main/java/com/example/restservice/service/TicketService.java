package com.example.restservice.service;

import com.example.restservice.entity.Ticket;
import com.example.restservice.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface TicketService {
    Collection<Ticket> findAll();
    Page<Ticket> findAll(PageRequest p);
    Ticket createOrUpdate(Ticket ticket);
    void delete(Long id) throws RecordNotFoundException;
    Ticket findById(Long id) throws RecordNotFoundException;
}
