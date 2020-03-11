package com.example.restservice.controller;

import com.example.restservice.service.AirportService;
import com.example.restservice.service.FlightService;
import com.example.restservice.service.SeatService;
import com.example.restservice.viewmodel.BookingProcessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(path = {"booking", ""})
public class BookingController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private AirportService airportService;
    @Autowired
    private SeatService seatService;

    @RequestMapping(path = {"/welcome", ""})
    public ModelAndView getAll(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("booking_search");
        model.addObject("bookingInfo", new BookingProcessInfo());
        model.addObject("locations", airportService.findDistinctLocations());
        return model;
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ModelAndView searchFlights(@ModelAttribute BookingProcessInfo bookingInfo) {
        ModelAndView model = new ModelAndView("booking_select_flight");
        model.addObject("departingSeats", seatService.findSuitableDepartingSlots(bookingInfo.departureLocation, bookingInfo.arrivalLocation, bookingInfo.departureDate));
        model.addObject("returningSeats", seatService.findSuitableReturningSlots(bookingInfo.arrivalLocation, bookingInfo.departureLocation, bookingInfo.returnDate));
        model.addObject("bookingInfo", bookingInfo);
        return model;
    }
}
