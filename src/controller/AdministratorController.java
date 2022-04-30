package com.example.project.controller;


import com.example.project.facades.AdministratorFacade;
import com.example.project.facades.AnonymousFacade;
import com.example.project.pojo.Administrator;
import com.example.project.pojo.AirlineCompany;
import com.example.project.pojo.Customer;
import com.example.project.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    private final AdministratorFacade administratorFacade = new AdministratorFacade(new LoginToken());
    @Autowired
    private AnonymousFacade anonymousFacade;


    @GetMapping("/all_customers")
    public List<Customer> getAllCustomers() {
        return administratorFacade.getAllCustomers();
    }

    @PostMapping("/add_airline")
    public void addAirline(@RequestBody AirlineCompany airlineCompany) {
        administratorFacade.addAirline(airlineCompany);
    }

    @PostMapping("/add_administrator")
    public void addAdministrator(@RequestBody Administrator administrator) {
        administratorFacade.addAdministrator(administrator);
    }

    @DeleteMapping("/remove_airline/{id}")
    public void removeAirline(@PathVariable int id) {
        administratorFacade.removeAirline(anonymousFacade.getAirlineById(id));
    }

    @DeleteMapping("/remove_customer/{id}")
    public void removeCustomer(@PathVariable int id) {
           administratorFacade.removeCustomer(administratorFacade.getAllCustomers().get(id));
    }

    @DeleteMapping("/remove_administrator/{id}")
    public void removeAdministrator(@PathVariable int id) {
        administratorFacade.removeAdministrator(administratorFacade.getAllAdministrators().get(id));
    }


}

