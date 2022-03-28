package facades;

import dao.*;

import pojo.AirlineCompany;
import pojo.Country;
import pojo.Flight;
import pojo.User;
import org.jetbrains.annotations.NotNull;


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

    public Object getFlightById(int id) {
        return flightDAO.get(id);
    }

    public List<Flight> getFlightsByParameters(int origin_country_id, int destination_country_id, Timestamp date) {
        return flightDAO.getFlightsByParameters(origin_country_id, destination_country_id, date);
    }

    public List<AirlineCompany> getAllAirlines() {
        return airlineCompanyDAO.getAll();
    }

    public Object getAirlineById(int id) {
        return airlineCompanyDAO.get(id);
    }

    public List<AirlineCompany> getAirlineByParameters(int country_id) {
        return airlineCompanyDAO.getAirlinesByCountry(country_id);
    }

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }

    public Object getCountryById(int id) {
        return countryDAO.get(id);
    }

    public void createNewUser(@NotNull User user) {
        if(user.userPassword.length()>5) userDAO.add(user);
        else {
            System.out.println("Error:The password must be at least six characters long");
        }
    }
}
