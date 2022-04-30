package com.example.project.dao;

import com.example.project.pojo.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
This CustomerDAO override all the CRUD functions
and the option to get customer by username.
 */
public class CustomerDAO implements DAO {
    //Instance of Connection and Statement.
    Connection_Statement cs = new Connection_Statement();
    //List of all the customers
    List<Customer> customerList = new ArrayList<>();

    //Get customer from DB.
    @Override
    public Customer get(int id) {
        Customer customer = null;
        try {
            ResultSet result = cs.getStatement().executeQuery("select * from Customers where Customer_Id =" + id);
            result.next();
            customer = new Customer(result.getLong("Customer_Id"),
                    result.getString("First_Name"),
                    result.getString("Last_Name"),
                    result.getString("Address"),
                    result.getString("Phone_No"),
                    result.getString("Credit_Card_No"),
                    result.getLong("user_Id"));
            cs.closeCS();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    //Get all the customers from DB.
    @Override
    public List<Customer> getAll() {
        try {
            ResultSet result = cs.getStatement().executeQuery("select * from Customers");
            while (result.next()) {
                customerList.add(new Customer(result.getLong("Customer_Id"),
                        result.getString("First_Name"),
                        result.getString("Last_Name"),
                        result.getString("Address"),
                        result.getString("Phone_No"),
                        result.getString("Credit_Card_No"),
                        result.getLong("User_Id")));
            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    //Add customer to DB.
    @Override
    public void add(Object o) {
        if (o instanceof Customer customer) {
            try {
                cs.getStatement().executeUpdate("insert into Customers" +
                        "(First_Name,Last_Name,Address,Phone_No,Credit_Card_No,User_Id)" +
                        "values" + "('" + customer.firstName + "','" + customer.lastName + "','" + customer.address
                        + "','" + customer.phoneNo + "','" + customer.creditCardNo + "'," +
                        customer.userId + ")");
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //Remove customer from DB.
    @Override
    public void remove(Object o) {
        if (o instanceof Customer customer) {
            try {
                cs.getStatement().executeUpdate("delete from Customers where Customer_Id =" +
                        customer.customerId);
                cs.closeCS();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Update a customer by the id.
    @Override
    public void update(Object o) {
        if (o instanceof Customer customer) {
            try {
                cs.getStatement().executeUpdate("update Customers set First_Name ='" + customer.firstName + "'," +
                        "Last_Name='" + customer.lastName + "'," + "Address='" + customer.address + "'," +
                        "Phone_No='" + customer.phoneNo + "'," + "Credit_Card_No='" + customer.creditCardNo +
                        "'," + "User_Id=" + customer.userId + "where Customer_Id = " + customer.customerId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Get customer by username from the stored procedures in DB.
    public Customer getCustomerByUsername(String username) {
        Customer customer = null;
        try {
            ResultSet result = cs.getStatement().executeQuery("select * from get_customer_by_username" + "('" + username + "')");
            while (result.next()) {
                customer = (new Customer(result.getLong("Customer_Id"),
                        result.getString("First_Name"),
                        result.getString("Last_Name"),
                        result.getString("Address"),
                        result.getString("Phone_No"),
                        result.getString("Credit_Card_No"),
                        result.getLong("Customers.User_Id")));
            }
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
}
