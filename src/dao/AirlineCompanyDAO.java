package dao;


import pojo.AirlineCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
This AirlineCompanyDAO override all the CRUD functions
and the option to get airline companies by username
and the option to get airline companies by country id.
 */
public class AirlineCompanyDAO implements DAO {
    //Instance of Connection and Statement.
    Connection_Statement cs = new Connection_Statement();
    //List of all the airline companies.
    List<AirlineCompany> airlineList = new ArrayList<>();
    //Get the airline company by id from DB.
    @Override
    public Object get(int id) {
        AirlineCompany airlineCompany = null;
        try {
            ResultSet result = cs.statement.executeQuery("select * from Airline_Companies where Airline_Company_Id =" + id);
            result.next();
            airlineCompany = new AirlineCompany(result.getLong("Airline_Company_Id"),
                    result.getString("Airline_Company_Name"),
                    result.getInt("Country_Id"),
                    result.getLong("User_Id"));
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompany;


    }
    //Get all the airline companies by id from DB.
    @Override
    public List<AirlineCompany> getAll() {
        try {
            ResultSet result = cs.statement.executeQuery("select * from Airline_Companies");
            while (result.next()) {
                airlineList.add(new AirlineCompany(result.getLong("Airline_Company_Id"),
                        result.getString("Airline_Company_Name"),
                        result.getInt("Country_Id"),
                        result.getLong("User_Id")));
                cs.closeCSR(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineList;
    }
    //Add the airline company to the DB.
    @Override
    public void add(Object o) {
        if (o instanceof AirlineCompany airlineCompany) {
            try {
                cs.statement.executeUpdate("insert into Airline_Companies(Airline_Company_Name,country_Id,user_Id)" +
                        "values" + "('" + airlineCompany.airlineCompanyName + "'," + airlineCompany.countryId + "," +
                        airlineCompany.userId + ")");
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //remove the airline company from DB.
    @Override
    public void remove(Object o) {
        if (o instanceof AirlineCompany airlineCompany) {
            try {
                cs.statement.executeUpdate("delete from Airline_Companies where Airline_Company_Id =" +
                        airlineCompany.airlineCompanyId);

                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Update the airline company by id from DB.
    @Override
    public void update(Object o) {
        if (o instanceof AirlineCompany airlineCompany) {
            try {
                cs.statement.executeUpdate("update Airline_Companies set Airline_Company_Name ='" +
                        airlineCompany.airlineCompanyName + "'," + "Country_Id =" + airlineCompany.countryId +
                        "," + "User_Id=" + airlineCompany.userId + "where Airline_Company_Id = " +
                        airlineCompany.airlineCompanyId);
                cs.closeCS();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //Get the airline company by country id from DB.
    public List<AirlineCompany> getAirlinesByCountry(int country_id) {
        List<AirlineCompany> airlineCountries = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from Airline_Companies where country_id=" + country_id);
            while (result.next()) {
                airlineCountries.add(new AirlineCompany(result.getLong("Airline_Company_Id"),
                        result.getString("Airline_Company_Name"),
                        result.getInt("Country_Id"),
                        result.getLong("User_Id")));
                cs.closeCSR(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCountries;
    }
    //Get airline company by username from the stored procedures in DB.
    public AirlineCompany getAirlineByUsername(String username) {
        AirlineCompany airlineCompany = null;
        try {
            ResultSet result = cs.statement.executeQuery("select * from get_airline_by_username" + "('" + username + "')");
            while (result.next()) {
                airlineCompany = (new AirlineCompany(result.getLong("Airline_Company_Id"),
                        result.getString("Airline_Company_Name"),
                        result.getInt("Country_Id"),
                        result.getLong("User_Id")));
                cs.closeCSR(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompany;
    }


}
