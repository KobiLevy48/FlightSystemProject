package dao;

import java.sql.*;
/*
This is a class for opening connection
 & statement and closing them.
 function to close ResultSet if null.
 */
public class Connection_Statement {
    //Statement
    public Statement statement;
    //Connection
    public Connection connection;
    //constructor opening connection & statement.
    public Connection_Statement() {
        try {
            this.connection = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/FlightSystem", "postgres", "kobi077");
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //close connection & statement.
    public void closeCS() {
        try {
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //close connection & statement & ResultSet.
    public void closeCSR(ResultSet result) {
        try {
            connection.close();
            statement.close();
            if (result != null) result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

