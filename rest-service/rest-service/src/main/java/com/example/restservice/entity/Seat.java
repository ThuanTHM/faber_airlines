/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.entity;

//import com.example.restservice.viewmodel.TargetView;
//import com.fasterxml.jackson.annotation.JsonView;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.*;

/**
 * @author FB-001
 */
@Entity
public class Seat {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int seatRank;

    public Seat(Long id, int seatRank, int availableNum, int totalNum, BigDecimal adultPrice, BigDecimal childPrice, BigDecimal infantPrice, Flight flight, Collection<Ticket> goTripTickets, Collection<Ticket> returnTripTickets) {
        this.id = id;
        this.seatRank = seatRank;
        this.availableNum = availableNum;
        this.totalNum = totalNum;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.infantPrice = infantPrice;
        this.flight = flight;
        this.goTripTickets = goTripTickets;
        this.returnTripTickets = returnTripTickets;
    }
    //available seat
    private int availableNum;
    //total seat
    //todo dumming for missing business's feature
    private int totalNum;//info of this field maybe moved into another entity storing Plane's info as soon as posible    
    private BigDecimal adultPrice;//temporarily input by user
    private BigDecimal childPrice;//temporarily input by user
    private BigDecimal infantPrice;//temporarily input by user
    //    price for 3 different age ranks depends on these infomations and based on algorithm which may be cover as soon as possibledeparture:
    // -NET seat price
    // -system & admin fee
    // -domestic passenger service charge depend on international/ national flight
    // -domestic itineries: (including VAT 10%) for adult depending on airport. Children from 2 to under 12 years pay 50% applicable adult rate. Infants under 2 years without seats are exempt.
    // (VAT: regularly by 10% of NET seat price + dps)
    private Flight flight;
    private Collection<Ticket> goTripTickets;
    private Collection<Ticket> returnTripTickets;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //seat rank
    @Basic
    @Column(name = "seat_rank", nullable = false)
    public int getSeatRank() {
        return seatRank;
    }

    public void setSeatRank(int seatRank) {
        this.seatRank = seatRank;
    }

    //number of available seats (= total - sold out)
    @Basic
    @Column(name = "available_num", nullable = false)
    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }

    //total seats
    @Basic
    @Column(name = "total_num", nullable = false)
    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalSeat) {
        this.totalNum = totalSeat;
    }

    //adult price
    @Basic
    @Column(name = "adult_price", nullable = false)
    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    //child price
    @Basic
    @Column(name = "child_price", nullable = false)
    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    //    infant price
    @Basic
    @Column(name = "infant_price", nullable = false)
    public BigDecimal getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(BigDecimal infantPrice) {
        this.infantPrice = infantPrice;
    }

    @OneToMany(mappedBy = "goTripSeat", fetch = FetchType.LAZY)
    public Collection<Ticket> getGoTripTickets() {
        return goTripTickets;
    }

    public void setGoTripTickets(Collection<Ticket> goTripTickets) {
        this.goTripTickets = goTripTickets;
    }

    @OneToMany(mappedBy = "returnTripSeat", fetch = FetchType.LAZY)
    public Collection<Ticket> getReturnTripTickets() {
        return returnTripTickets;
    }

    public void setReturnTripTickets(Collection<Ticket> returnTripTickets) {
        this.returnTripTickets = returnTripTickets;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", referencedColumnName = "id", nullable = false)
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
