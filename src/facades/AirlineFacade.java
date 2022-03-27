package facades;

import pojo.AirlineCompany;
import pojo.Flight;
import token.LoginToken;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AirlineFacade extends AnonymousFacade {

    private final LoginToken loginToken;

    public AirlineFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public List<Flight> getMyFlights() {
        return flightDAO.getFlightsByAirlineId(loginToken.getId());
    }

    public void updateAirline(@NotNull AirlineCompany airline) {
        if (checkToken(airline.airlineCompanyId)) airlineCompanyDAO.update(airline);
        else {
            System.out.println("Error:you can't update another company flight");
        }
    }

    public void addFlight(@NotNull Flight flight) {
        if ((flight.remainingTickets >= 0) && (flight.originCountryId != flight.destinationCountryId) &&
                (flight.landingTime.after(flight.departureTime)) &&
                (checkToken(flight.airlineCompanyId))) flightDAO.add(flight);
        else {
            System.out.println("Error:something went wrong");
        }
    }

    public void updateFlight(@NotNull Flight flight) {
        if ((flight.remainingTickets >= 0) && (flight.originCountryId != flight.destinationCountryId) &&
                (flight.landingTime.after(flight.departureTime)) &&
                (checkToken(flight.airlineCompanyId))) flightDAO.update(flight);
        else {
            System.out.println("Error:something went wrong");
        }
    }

    public void removeFlight(@NotNull Flight flight) {
        if (checkToken(flight.airlineCompanyId)) flightDAO.remove(flight);
        else {
            System.out.println("Error:you can't remove another company flight");
        }
    }

    public boolean checkToken(long id) {
        return loginToken.getId() == id;
    }
}
