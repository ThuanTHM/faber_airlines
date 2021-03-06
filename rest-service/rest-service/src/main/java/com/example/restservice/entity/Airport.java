/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.entity;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;

import javax.persistence.*;
import java.util.Collection;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author FB-001
 */
@Entity
@Table(name = "airport", schema = "dbexample")
public class Airport {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name = "";
    private String location = "";
    private Boolean enable = true;
    private Collection<Flight> departureFlights = new ArrayList<>();
    private Collection<Flight> arrivalFlights = new ArrayList<>();


    public Airport() {
    }

    public Airport(String name, String location, Boolean enable) {
        this.name = name;
        this.location = location;
        this.enable = enable;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "location", nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "enable", nullable = true)
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @OneToMany(mappedBy = "departureAirport", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Collection<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public void setDepartureFlights(Collection<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }

    @OneToMany(mappedBy = "arrivalAirport", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Collection<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setArrivalFlights(Collection<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }
}
