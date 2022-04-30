package com.example.project.controller;


import com.example.project.facades.AirlineFacade;
import com.example.project.facades.AnonymousFacade;
import com.example.project.facades.FacadeBase;
import com.example.project.pojo.AirlineCompany;
import com.example.project.pojo.Flight;
import com.example.project.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    private final AirlineFacade airlineFacade =new AirlineFacade(new LoginToken());
    @Autowired
    private AnonymousFacade anonymousFacade;

    @GetMapping("/flights")
    public List<Flight> getMyFlights(){
        return airlineFacade.getMyFlights();
    }

    @PutMapping("/update")
    public void updateAirline(@RequestBody AirlineCompany airlineCompany){
        airlineFacade.updateAirline(airlineCompany);
    }
    @PostMapping("/add_flight")
    public void addFlight(@RequestBody Flight flight){
        airlineFacade.addFlight(flight);
    }

    @PutMapping("/update_flight")
    public void updateFlight(@RequestBody Flight flight){
        airlineFacade.updateFlight(flight);
    }

    @DeleteMapping("/remove_flight/{id}")
    public void removeFlight(@PathVariable int id){
        airlineFacade.removeFlight(anonymousFacade.getFlightById(id));
    }


}
