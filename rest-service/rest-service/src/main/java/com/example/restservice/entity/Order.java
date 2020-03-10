/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author FB-001
 */
@Entity
@Table(name = "orderinf")
public class Order {
    private static final long serialVersionUID = 1L;

    private Long id;//order's id
    private Timestamp orderTime;//time created of order, maybe at last modified before order being processed
    private boolean returntrip;//return trip or not (one-way trip)
    private String contactName;
    private BigDecimal contactPhoneNum;
    private String contactEmail;
    private String contactAddress;
    private Collection<Ticket> tickets;    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //todo set nullable = false
    @Basic
    @Column(name = "order_time", nullable = true)
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    //todo set nullable = false
    @Basic
    @Column(name = "return_trip", nullable = true)
    public boolean isReturntrip() {
        return returntrip;
    }

    public void setReturntrip(boolean returntrip) {
        this.returntrip = returntrip;
    }

    //todo set nullable = false
    @Basic
    @Column(name = "contact_name", nullable = true)
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    //todo set nullable = false
    @Basic
    @Column(name = "contact_phone_num", nullable = true)
    public BigDecimal getContactPhoneNum() {
        return contactPhoneNum;
    }

    public void setContactPhoneNum(BigDecimal contactPhoneNum) {
        this.contactPhoneNum = contactPhoneNum;
    }

    //todo set nullable = false
    @Basic
    @Column(name = "contact_email", nullable = true)
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    //todo set nullable = false
    @Basic
    @Column(name = "contact_address", nullable = true)
    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
    
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
