package com.example.restservice.controller;

import com.example.restservice.entity.Flight;
import com.example.restservice.entity.Seat;
import com.example.restservice.exception.RecordNotFoundException;
import com.example.restservice.service.AirportService;
import com.example.restservice.service.FlightService;
import com.example.restservice.service.SeatService;
import com.example.restservice.viewmodel.BookingProcessInfo;
import com.example.restservice.viewmodel.OrderForm;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(path = {"booking"})
public class BookingController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private AirportService airportService;
    @Autowired
    private SeatService seatService;

    @GetMapping(path = "/search_flight")
    public ModelAndView getAll(HttpServletRequest request) {
        
        ModelAndView model = new ModelAndView("booking_search");
        model.addObject("bookingInfo", new BookingProcessInfo());
        model.addObject("locations", airportService.findAll());
        return model;
    }
}
