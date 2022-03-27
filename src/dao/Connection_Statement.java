package dao;


import java.sql.*;

public class Connection_Statement {
    public Statement statement;
    public Connection connection;

    public Connection_Statement() {
        try {
            this.connection = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:5432/FlightSystem", "postgres", "kobi077");
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeCS() {
        try {
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

