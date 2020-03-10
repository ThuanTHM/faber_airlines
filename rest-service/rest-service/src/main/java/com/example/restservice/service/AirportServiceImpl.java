package com.example.restservice.service;

import com.example.restservice.entity.Airport;
import com.example.restservice.exception.RecordNotFoundException;
import com.example.restservice.jpa.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service("airportService")
public class AirportServiceImpl implements AirportService {
    static Logger logger = Logger.getLogger(AirportServiceImpl.class.getName());

    @Autowired
    private AirportRepository airportRepository;

    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public void delete(Long id) throws RecordNotFoundException {
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            airportRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    @Override
    public Collection<Airport> filtering(Airport airport) {
        return null;
    }

    public Collection<Airport> findAll() {
        return airportRepository.findAll();
    }

    public Page<Airport> findAll(PageRequest p) {
        return airportRepository.findAll(p);
    }

    @Override
    public Airport findById(Long id) throws RecordNotFoundException {
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            return airport.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    @Override
    public Airport createOrUpdate(Airport airport) {
        if (airport.getId() == null) {
            airport = airportRepository.save(airport);

            return airport;
        } else {
            Optional<Airport> a = airportRepository.findById(airport.getId());
            if (a.isPresent()) {
                Airport newEntity = a.get();
                newEntity.setName(airport.getName());
                newEntity.setLocation(airport.getLocation());
                newEntity = airportRepository.save(newEntity);
                return newEntity;
            } else {
                airport = airportRepository.save(airport);
                return airport;
            }
        }
    }

    public Airport findByName(String name) {
        return airportRepository.findByName(name);
    }

}
