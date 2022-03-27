package dao;

import pojo.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO implements DAO {

    Connection_Statement cs = new Connection_Statement();
    List<Administrator> administratorsList = new ArrayList<>();

    @Override
    public Administrator get(int id) {
        Administrator administrator = null;

        try {
            ResultSet result = cs.statement.executeQuery("select * from administrators where Administrator_Id=" + id);
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

    @Override
    public List<Administrator> getAll() {
        try {
            ResultSet result = cs.statement.executeQuery("select * from Administrators");
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


    @Override
    public void add(Object o) {
        if (o instanceof Administrator administrator) {
            try {
                cs.statement.executeUpdate("insert into Administrators (First_Name,Last_Name,User_Id )" +
                        "values" + "('" + administrator.firstName + "','" + administrator.lastName + "'," + administrator.userId + ")");
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void remove(Object o) {
        if (o instanceof Administrator administrator) {
            try {
                cs.statement.executeUpdate("delete from Administrators where administrator_Id = " + administrator.administratorId);
                //cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(Object o) {
        if (o instanceof Administrator administrator) {
            try {
                cs.statement.executeUpdate("update Administrators set First_Name= '" + administrator.firstName +
                        "'" + ", Last_Name= '" + administrator.lastName + "'" + ",User_Id=" +
                        administrator.userId + "where Administrator_Id=" + administrator.administratorId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public Administrator getAdministratorByUsername(String username) {
        Administrator administrator = null;
        try {

            ResultSet result = cs.statement.executeQuery("select * from get_administrator_by_username('"+ username+"')");
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
