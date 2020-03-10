package com.example.restservice.controller;

import com.example.restservice.entity.Airport;
import com.example.restservice.entity.Flight;
import com.example.restservice.exception.RecordNotFoundException;
import com.example.restservice.service.AirportService;
import com.example.restservice.service.FlightService;
import com.example.restservice.viewmodel.OrderForm;
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
@RequestMapping(path = {"flight", "/"})
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private AirportService airportService;

    @GetMapping(path = "/manage")
    public ModelAndView getAll(HttpServletRequest request) {
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 3; //default page size is 10
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        ModelAndView model = new ModelAndView("flight_manage");
        model.addObject("flights", flightService.findAll(PageRequest.of(page, size)));
        return model;
    }

    @RequestMapping(path = {"/manage/edit", "/manage/edit/{id}"})
    public String edit(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Flight flight = flightService.findById(id.get());
            model.addAttribute("flightDetail", flight);
        } else {
            model.addAttribute("flightDetail", new Flight(Calendar.getInstance().getTime(), Calendar.getInstance().getTime()));
            model.addAttribute("airports", airportService.findAll());
        }
        return "flight_modify";
    }

    @RequestMapping(path = {"/manage/delete/{id}"})
    public String delete(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        flightService.delete(id);
        return "redirect:/flight/manage";
    }

    @RequestMapping(path = "/manage/create", method = RequestMethod.POST) // Map ONLY POST Requests
    public String addNewUser(@ModelAttribute Airport airportDetail) {

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        airportService.createOrUpdate(airportDetail);
        return "redirect:/airport/manage";
    }

    @GetMapping(path = "/booking/search")
    public ModelAndView search() {
//        Timestamp t = new Timestamp(System.currentTimeMillis());
//        for(int i=0;i<100;i++){
//            int rand = (int)(Math.random() * airports.size()) + 1;
//            flightService.create(new Flight(
//                    new Timestamp(t.int)(Math.random() * airports.size()))
//            ));
//        }
        return searchFlightPage();
    }

    @PostMapping(path = "/booking/select-flights") // Map ONLY POST Requests
    public ModelAndView search(@ModelAttribute OrderForm orderForm) {

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        ModelAndView model = new ModelAndView("flight_select");
        return model;
    }

    public ModelAndView searchFlightPage() {
        ModelAndView model = new ModelAndView("flight_search");
        model.addObject("airports", airportService.findAll());
        model.addObject("orderForm", new OrderForm());
        return model;
    }
}
