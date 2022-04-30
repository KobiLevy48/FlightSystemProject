package com.example.project.controller;

import com.example.project.facades.AnonymousFacade;
import com.example.project.facades.FacadeBase;
import com.example.project.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class AnonymousController {
    @Autowired
    private AnonymousFacade anonymousFacade;

    @PostMapping("/add_customer")
    public void addTicket(@RequestBody User user, @RequestBody Customer customer){
        anonymousFacade.addCustomer(user,customer);
    }
    @GetMapping("/all_flights")
    public List<Flight> getAllFlights(){
        return anonymousFacade.getAllFlights();
    }

    @GetMapping("/flight_by_id/{id}")
    public Flight getFlightById(@PathVariable int id){
        return anonymousFacade.getFlightById(id);
    }

    @GetMapping("/flights_by_parameters/{or_id}/{de_id}/{date}")
    public List<Flight> getFlightsByParameters(@PathVariable int or_id, @PathVariable int de_id, @PathVariable Timestamp date){
        return anonymousFacade.getFlightsByParameters(or_id,de_id,date);
    }

    @GetMapping("/all_airlines")
    public List<AirlineCompany> getAllAirlines(){
        return anonymousFacade.getAllAirlines();
    }

    @GetMapping("/airline_by_id/{id}")
    public AirlineCompany getAirlineById(@PathVariable int id){
        return anonymousFacade.getAirlineById(id);
    }

    @GetMapping("/airline_by_parameter/{id}")
    public List<AirlineCompany> getAirlineByParameter(@PathVariable int id){
        return anonymousFacade.getAirlineByParameter(id);
    }

    @GetMapping("/all_countries")
    public List<Country> getAllCountries(){
        return anonymousFacade.getAllCountries();
    }

    @GetMapping("/country_by_id/{id}")
    public Country getCountryById(@PathVariable int id){
        return anonymousFacade.getCountryById(id);
    }

    @PostMapping("/add_user")
    public void createNewUser(@RequestBody User user){
        anonymousFacade.createNewUser(user);
    }


}
