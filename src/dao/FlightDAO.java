package dao;

import org.jetbrains.annotations.NotNull;
import pojo.Customer;
import pojo.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO implements DAO {
    Connection_Statement cs = new Connection_Statement();
    List<Flight> flightList = new ArrayList<>();

    @Override
    public Object get(int id) {
        Flight flight = null;
        try {
            ResultSet result = cs.statement.executeQuery("select * from Flights where Flight_Id = " + id);
            result.next();
            flight = new Flight(result.getLong("Flight_Id"),
                    result.getLong("Airline_Company_Id"),
                    result.getInt("Origin_Country_Id"),
                    result.getInt("Destination_Country_Id"),
                    result.getTimestamp("Departure_Time"),
                    result.getTimestamp("Landing_Time"),
                    result.getInt("Remaining_Tickets"));
            cs.closeCSR(result);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        try {
            ResultSet result = cs.statement.executeQuery("select * from Flights");
            while (result.next()) {
                flightList.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightList;
    }

    @Override
    public void add(Object o) {
        if (o instanceof Flight flight) {
            try {
                cs.statement.executeUpdate("insert into Flights" +
                        "(Airline_Company_Id,Origin_Country_Id,Destination_Country_Id,Departure_Time,Landing_Time,Remaining_Tickets)" +
                        "values" + "(" + flight.airlineCompanyId + "," + flight.originCountryId + "," + flight.destinationCountryId
                        + ",'" + flight.departureTime + "','" + flight.landingTime + "'," +
                        flight.remainingTickets + ")");
                cs.closeCS();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof Flight flight) {
            try {
                cs.statement.executeUpdate("delete from Flights where Flight_Id =" +
                        flight.flightId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Flight flight) {
            try {
                cs.statement.executeUpdate("update Flights set Airline_Company_Id ='" + flight.airlineCompanyId + "'," +
                        "Origin_Country_Id='" + flight.originCountryId + "'," + "Destination_Country_Id='" + flight.destinationCountryId + "'," +
                        "Departure_Time='" + flight.departureTime + "'," + "Landing_Time='" + flight.landingTime +
                        "'," + "Remaining_Tickets=" + flight.remainingTickets + "where Flight_Id = " + flight.flightId
                );
                cs.closeCS();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Flight> getFlightsByOriginCountryId(int country_id) {
        List<Flight> flightsByOriginCountryId = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from Flights where Origin_Country_Id =" + country_id);
            while (result.next()) {
                flightsByOriginCountryId.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightsByOriginCountryId;
    }

    public List<Flight> getFlightsByDestinationCountryId(int country_id) {
        List<Flight> flightsByDestinationCountryId = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from Flights where Destination_Country_Id =" + country_id);
            while (result.next()) {
                flightsByDestinationCountryId.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightsByDestinationCountryId;
    }

    public List<Flight> getFlightsByDepartureDate(LocalDate date) {
        List<Flight> flightsByDepartureDate = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from Flights where Departure_Time ::Date =" + "'" + date + "'");
            while (result.next()) {
                flightsByDepartureDate.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return flightsByDepartureDate;
    }

    public List<Flight> getFlightsByLandingDate(LocalDate date) {

        List<Flight> flightsByLandingDate = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from Flights where Landing_Time ::Date =" + "'" + date + "'");
            while (result.next()) {
                flightsByLandingDate.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightsByLandingDate;
    }

    public List<Flight> getFlightsByCustomer(@NotNull Customer customer) {
        List<Flight> FlightsByCustomer = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select flight_id,airline_company_id,origin_country_id," +
                    "destination_country_id,departure_time,landing_time,remaining_tickets " +
                    "from get_tickets_by_customer(" +
                    customer.customerId + ")join flights on \"Flight_Id\" =" + "Flights.Flight_Id");
            while (result.next()) {
                FlightsByCustomer.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return FlightsByCustomer;
    }

    public List<Flight> getFlightsByParameters(int origin_country_id, int destination_country_id, Timestamp date) {
        List<Flight> get_flights_by_parameters = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from get_flights_by_parameters(" +
                    origin_country_id + "," + destination_country_id + "," + "'" + date + "')");
            while (result.next()) {
                get_flights_by_parameters.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));
            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get_flights_by_parameters;
    }

    public List<Flight> getFlightsByAirlineId(long airline_id) {
        List<Flight> get_flights_by_airline_id = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from get_flights_by_airline_id(" + airline_id + ")");
            while (result.next()) {
                get_flights_by_airline_id.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get_flights_by_airline_id;
    }

    public List<Flight> getArrivalFlights(int country_id) {
        List<Flight> get_arrival_flights = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from get_arrival_flights(" + country_id + ")");
            while (result.next()) {
                get_arrival_flights.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));

            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get_arrival_flights;
    }

    public List<Flight> getDepartureFlights(int country_id) {
        List<Flight> get_departure_flights = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from get_departure_flights(" + country_id + ")");
            while (result.next()) {
                get_departure_flights.add(new Flight(result.getLong("Flight_Id"),
                        result.getLong("Airline_Company_Id"),
                        result.getInt("Origin_Country_Id"),
                        result.getInt("Destination_Country_Id"),
                        result.getTimestamp("Departure_Time"),
                        result.getTimestamp("Landing_Time"),
                        result.getInt("Remaining_Tickets")));
            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get_departure_flights;
    }


}
