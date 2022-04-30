package com.example.project.facades;

import com.example.project.pojo.Administrator;
import com.example.project.pojo.AirlineCompany;
import com.example.project.pojo.Customer;
import com.example.project.token.LoginToken;


import java.util.List;

public class AdministratorFacade extends AnonymousFacade {
    private final LoginToken loginToken;

    public AdministratorFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }
    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public void addAirline(AirlineCompany airline) {
        airlineCompanyDAO.add(airline);
    }

    public void addAdministrator(Administrator administrator) {
        administratorDAO.add(administrator);
    }

    public void removeAirline(AirlineCompany airlineCompany) {
        airlineCompanyDAO.remove(airlineCompany);
    }

    public void removeCustomer(Customer customer) {
        customerDAO.remove(customer);
    }

    public void removeAdministrator(Administrator administrator) {
        administratorDAO.remove(administrator);
    }
    public List<Administrator> getAllAdministrators(){
        return administratorDAO.getAll();
    }
}
