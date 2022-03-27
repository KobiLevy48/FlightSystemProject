package dao;


import pojo.AirlineCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompanyDAO implements DAO {
    Connection_Statement cs = new Connection_Statement();
    List<AirlineCompany> airlineList = new ArrayList<>();

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
