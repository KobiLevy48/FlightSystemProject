package com.example.project.dao;

import com.example.project.pojo.Administrator;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
This AdministratorDAO override all the CRUD functions
and the option to get administrator by username.
 */
public class AdministratorDAO implements DAO {
    //Instance of Connection and Statement.
    Connection_Statement cs = new Connection_Statement();
    //List of all the administrators.
    List<Administrator> administratorsList = new ArrayList<>();

    //Get the administrator by id from DB.
    @Override
    public Administrator get(int id) {
        Administrator administrator = null;

        try {
            ResultSet result = cs.getStatement().executeQuery("select * from administrators where Administrator_Id=" + id);
            result.next();
            administrator = new Administrator(result.getInt("administrator_Id"),
                    result.getString("first_Name"),
                    result.getString("last_Name"),
                    result.getLong("user_Id"));
            //cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrator;

    }
    //Get all the administrators from DB.
    @Override
    public List<Administrator> getAll() {
        try {
            ResultSet result = cs.getStatement().executeQuery("select * from Administrators");
            while (result.next()) {
                administratorsList.add(new Administrator(result.getInt("administrator_Id"),
                        result.getString("first_Name"),
                        result.getString("last_Name"),
                        result.getLong("user_Id")));
            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administratorsList;
    }
    //Add administrator to the DB.
    @Override
    public void add(Object o) {
        if (o instanceof Administrator administrator) {
            try {
                cs.getStatement().executeUpdate("insert into Administrators (First_Name,Last_Name,User_Id )" +
                        "values" + "('" + administrator.firstName + "','" + administrator.lastName + "'," + administrator.userId + ")");
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //Remove administrator from DB.
    @Override
    public void remove(Object o) {
        if (o instanceof Administrator administrator) {
            try {
                cs.getStatement().executeUpdate("delete from Administrators where administrator_Id = " + administrator.administratorId);
                //cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //Update administrator by id from DB.
    @Override
    public void update(Object o) {
        if (o instanceof Administrator administrator) {
            try {
                cs.getStatement().executeUpdate("update Administrators set First_Name= '" + administrator.firstName +
                        "'" + ", Last_Name= '" + administrator.lastName + "'" + ",User_Id=" +
                        administrator.userId + "where Administrator_Id=" + administrator.administratorId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    //Get administrator by username from the stored procedures in DB.
    public Administrator getAdministratorByUsername(String username) {
        Administrator administrator = null;
        try {

            ResultSet result = cs.getStatement().executeQuery("select * from get_administrator_by_username('"+ username+"')");
            result.next();
            administrator = new Administrator(result.getInt("administrator_Id"),
                    result.getString("first_Name"),
                    result.getString("last_Name"),
                    result.getLong("user_Id"));
            //cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrator;

    }
}
