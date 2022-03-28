package dao;

import pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
This UserDAO override all the CRUD functions
and the option to get user by username.
 */
public class UserDAO implements DAO {
    //Instance of Connection and Statement.
    Connection_Statement cs = new Connection_Statement();
    //List of all users.
    List<User> userList = new ArrayList<>();
    //Get user by id from DB.
    @Override
    public Object get(int id) {
        User user = null;
        try {
            ResultSet result = cs.statement.executeQuery("select * from Users where User_Id=" + id);
            result.next();
            user = new User(result.getLong("User_Id"),
                    result.getString("Username"),
                    result.getString("User_Password"),
                    result.getString("Email"),
                    result.getInt("User_Role"));
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    //Get all users from DB.
    @Override
    public List<User> getAll() {
        try {
            ResultSet result = cs.statement.executeQuery("select * from Users");
            while (result.next()) {
                userList.add(new User(result.getLong("User_Id"),
                        result.getString("Username"),
                        result.getString("User_Password"),
                        result.getString("Email"),
                        result.getInt("User_Role")));
                cs.closeCSR(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    //Add user to DB.
    @Override
    public void add(Object o) {
        if (o instanceof User user) {
            try {
                cs.statement.executeUpdate("insert into Users(Username,User_Password,Email,User_Role)" +
                        "values" + "('" + user.username + "','" + user.userPassword + "','" +
                        user.email + "'," + user.userRole + ")");
                cs.closeCS();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Remove user by id.
    @Override
    public void remove(Object o) {
        if (o instanceof User user) {
            try {
                cs.statement.executeUpdate("delete from Users where User_Id =" +
                        user.userId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Update user by id.
    @Override
    public void update(Object o) {
        if (o instanceof User user) {
            try {
                cs.statement.executeUpdate("update Users set Username ='" + user.username + "'," +
                        "User_Password='" + user.userPassword + "'," + "Email='" + user.email + "'," +
                        "User_Role=" + user.userRole + "where User_Id = " + user.userId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Get user by username from the stored procedures in DB.
    public User getUserByUsername(String username) {
        User user = null;
        try {
            ResultSet result = cs.statement.executeQuery("select * from get_user_by_username" + "('" + username + "')");
            result.next();
            user = (new User(result.getLong("User_Id"),
                    result.getString("Username"),
                    result.getString("User_Password"),
                    result.getString("Email"),
                    result.getInt("User_Role")));


            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
