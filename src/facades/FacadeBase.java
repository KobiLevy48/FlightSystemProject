package com.example.project.facades;

import com.example.project.dao.*;
import com.example.project.pojo.*;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.util.List;

public abstract class FacadeBase {
    //Instances of all dao's.
    protected final FlightDAO flightDAO = new FlightDAO();
    protected final AirlineCompanyDAO airlineCompanyDAO = new AirlineCompanyDAO();
    protected final CountryDAO countryDAO = new CountryDAO();
    protected final UserDAO userDAO = new UserDAO();
    protected final CustomerDAO customerDAO = new CustomerDAO();
    protected final AdministratorDAO administratorDAO = new AdministratorDAO();
    protected final TicketDAO ticketDAO = new TicketDAO();

    //Use dao function
    public List<Flight> getAllFlights() {
        return flightDAO.getAll();
    }

    public Flight getFlightById(int id) {
        return flightDAO.get(id);
    }

    public List<Flight> getFlightsByParameters(int origin_country_id, int destination_country_id, Timestamp date) {
        return flightDAO.getFlightsByParameters(origin_country_id, destination_country_id, date);
    }

    public List<AirlineCompany> getAllAirlines() {
        return airlineCompanyDAO.getAll();
    }

    public AirlineCompany getAirlineById(int id) {
        return airlineCompanyDAO.get(id);
    }

    public List<AirlineCompany> getAirlineByParameter(int country_id) {
        return airlineCompanyDAO.getAirlinesByCountry(country_id);
    }

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }

    public Country getCountryById(int id) {
        return countryDAO.get(id);
    }

    public void createNewUser(@NotNull User user) {
        if(user.userPassword.length()>5) userDAO.add(user);
        else {
            System.out.println("Error:The password must be at least six characters long");
        }
    }
}
