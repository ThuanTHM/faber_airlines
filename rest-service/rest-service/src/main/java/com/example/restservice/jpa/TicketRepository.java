/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice.jpa;

import com.example.restservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author FB-001
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
