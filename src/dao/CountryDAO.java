package dao;

import pojo.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements DAO {

    Connection_Statement cs = new Connection_Statement();
    List<Country> countryList = new ArrayList<>();

    @Override
    public Object get(int id) {
        Country country = null;

        try {
            ResultSet result = cs.statement.executeQuery("select * from Countries where Country_Id =" + id);
            result.next();
            country = new Country(result.getInt("Country_Id"),
                    result.getString("Country_Name"));
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

    @Override
    public List<Country> getAll() {

        try {
            ResultSet result = cs.statement.executeQuery("select * from Countries");
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

    @Override
    public void add(Object o) {
        if (o instanceof Country country) {
            try {
                cs.statement.executeUpdate("insert into Countries(Country_Name)" +
                        "values" + "('" + country.countryName + "')");
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof Country country) {
            try {
                cs.statement.executeUpdate("delete from Countries where Country_Id =" +
                        country.countryId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Country country) {
            try {
                cs.statement.executeUpdate("update Countries set Country_Name ='" +
                        country.countryName + "'where Country_Id = " + country.countryId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
