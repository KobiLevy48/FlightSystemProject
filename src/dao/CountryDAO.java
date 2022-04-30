package com.example.project.dao;

import com.example.project.pojo.Country;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//This CountryDAO override all the CRUD functions.

public class CountryDAO implements DAO {
    //Instance of Connection and Statement.
    Connection_Statement cs = new Connection_Statement();
    //List of all the countries.
    List<Country> countryList = new ArrayList<>();

    //Get country by id from DB.
    @Override
    public Country get(int id) {
        Country country = null;

        try {
            ResultSet result = cs.getStatement().executeQuery("select * from Countries where Country_Id =" + id);
            result.next();
            country = new Country(result.getInt("Country_Id"),
                    result.getString("Country_Name"));
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }
    //Get all countries from DB.
    @Override
    public List<Country> getAll() {

        try {
            ResultSet result = cs.getStatement().executeQuery("select * from Countries");
            while (result.next()) {
                countryList.add(new Country(result.getInt("Country_Id"),
                        result.getString("Country_Name")));
            }
            cs.closeCSR(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryList;
    }
    //Add country to the DB.
    @Override
    public void add(Object o) {
        if (o instanceof Country country) {
            try {
                cs.getStatement().executeUpdate("insert into Countries(Country_Name)" +
                        "values" + "('" + country.countryName + "')");
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Remove country from DB by id.
    @Override
    public void remove(Object o) {
        if (o instanceof Country country) {
            try {
                cs.getStatement().executeUpdate("delete from Countries where Country_Id =" +
                        country.countryId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Update country by id.
    @Override
    public void update(Object o) {
        if (o instanceof Country country) {
            try {
                cs.getStatement().executeUpdate("update Countries set Country_Name ='" +
                        country.countryName + "'where Country_Id = " + country.countryId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
