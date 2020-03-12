/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.viewmodel;

import com.example.restservice.entity.Order;
import com.example.restservice.entity.Seat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author FB-001
 */
public class BookingProcessInfo {

    public String departureLocation;
    public String arrivalLocation;
    public int numOfAdult;
    public int numOfChildren;
    public int numOfInfant;
    public Date departureDate;
    public Date returnDate; 
    public Seat departingTrip = new Seat();//departing seat id
    public Seat returningTrip = new Seat();//returning seat id
    public Order order = new Order();

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public int getNumOfAdult() {
        return numOfAdult;
    }

    public void setNumOfAdult(int numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    public int getNumOfInfant() {
        return numOfInfant;
    }

    public void setNumOfInfant(int numOfInfant) {
        this.numOfInfant = numOfInfant;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Seat getDepartingTrip() {
        return departingTrip;
    }

    public void setDepartingTrip(Seat departingTrip) {
        this.departingTrip = departingTrip;
    }

    public Seat getReturningTrip() {
        return returningTrip;
    }

    public void setReturningTrip(Seat returningTrip) {
        this.returningTrip = returningTrip;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    } 
}
