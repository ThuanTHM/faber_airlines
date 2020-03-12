package com.example.restservice.controller;

import com.example.restservice.entity.Ticket;
import com.example.restservice.exception.RecordNotFoundException;
import com.example.restservice.service.AirportService;
import com.example.restservice.service.FlightService;
import com.example.restservice.service.SeatService;
import com.example.restservice.viewmodel.BookingProcessInfo;
import java.math.BigDecimal;
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

    /**
     *
     * @param request
     * @return booking_search for flights with bookingInfo model
     */
    @RequestMapping(path = {"/search", ""})
    public ModelAndView getAll(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("booking_search");
        model.addObject("bookingInfo", new BookingProcessInfo());
        model.addObject("locations", airportService.findDistinctLocations());
        return model;
    }

    /**
     *
     * @param bookingInfo
     * @return
     */
    @RequestMapping(path = "/select/flight", method = RequestMethod.POST)
    public ModelAndView searchFlights(@ModelAttribute BookingProcessInfo bookingInfo) {
        ModelAndView model = new ModelAndView("booking_select_flight");
        model.addObject("departingSeats", seatService.findSuitableDepartingSlots(bookingInfo.departureLocation, bookingInfo.arrivalLocation, bookingInfo.departureDate));
        if (bookingInfo.order.isRoundticket()) {
            model.addObject("returningSeats", seatService.findSuitableReturningSlots(bookingInfo.arrivalLocation, bookingInfo.departureLocation, bookingInfo.returnDate));
        }
        model.addObject("bookingInfo", bookingInfo);
        return model;
    }

    /**
     *
     * @param bookingInfo
     * @return
     * @throws RecordNotFoundException
     */
    @RequestMapping(path = "/order/contact", method = RequestMethod.POST)
    public ModelAndView selectFlights(@ModelAttribute BookingProcessInfo bookingInfo) throws RecordNotFoundException {
        ModelAndView model = new ModelAndView("booking_ticket_filling");
        int i;
        for (i = 0; i < bookingInfo.numOfAdult; i++) {//ageRank  = 0 (adult)
            bookingInfo.order.getTickets().add(
                    new Ticket(
                            bookingInfo.order.isRoundticket(),
                            0,
                            //                            seatService.findById(bookingInfo.getDepartingTrip()).getAdultPrice(),
                            bookingInfo.getDepartingTrip().getAdultPrice(),
                            //                            (bookingInfo.order.isRoundticket() ? seatService.findById(bookingInfo.getReturningTrip()).getAdultPrice() : BigDecimal.ZERO
                            (bookingInfo.order.isRoundticket() ? bookingInfo.getReturningTrip().getAdultPrice() : BigDecimal.ZERO),
                            //                            seatService.findById(bookingInfo.getDepartingTrip())
                            bookingInfo.getDepartingTrip(),
                            //                            seatService.findById(bookingInfo.getReturningTrip())
                            bookingInfo.getReturningTrip()
                    ));
        }
        for (i = 0; i < bookingInfo.numOfChildren; i++) {//ageRank  = 1 (children) 
            bookingInfo.order.getTickets().add(
                    new Ticket(
                            bookingInfo.order.isRoundticket(),
                            1,
                            //                            seatService.findById(bookingInfo.getDepartingTrip()).getChildPrice(),
                            bookingInfo.getDepartingTrip().getChildPrice(),
                            //                            (bookingInfo.order.isRoundticket() ? seatService.findById(bookingInfo.getReturningTrip()).getChildPrice() : BigDecimal.ZERO),
                            (bookingInfo.order.isRoundticket() ? bookingInfo.getReturningTrip().getChildPrice() : BigDecimal.ZERO),
                            //                            seatService.findById(bookingInfo.getDepartingTrip()),
                            bookingInfo.getDepartingTrip(),
                            //                            seatService.findById(bookingInfo.getReturningTrip())
                            bookingInfo.getReturningTrip()
                    ));
        }
        for (i = 0; i < bookingInfo.numOfInfant; i++) {//ageRank  = 0 (infant)
            bookingInfo.order.getTickets().add(
                    new Ticket(
                            bookingInfo.order.isRoundticket(),
                            2,
                            //                            seatService.findById(bookingInfo.getDepartingTrip()).getInfantPrice(),
                            bookingInfo.getDepartingTrip().getInfantPrice(),
                            //                            (bookingInfo.order.isRoundticket() ? seatService.findById(bookingInfo.getReturningTrip()).getInfantPrice() : BigDecimal.ZERO),
                            (bookingInfo.order.isRoundticket() ? bookingInfo.getReturningTrip().getInfantPrice() : BigDecimal.ZERO),
                            //                            seatService.findById(bookingInfo.getDepartingTrip()),
                            bookingInfo.getDepartingTrip(),
                            //                            seatService.findById(bookingInfo.getReturningTrip())
                            bookingInfo.getReturningTrip()
                    ));
        }
        model.addObject("bookingInfo", bookingInfo);
        return model;
    }
}
