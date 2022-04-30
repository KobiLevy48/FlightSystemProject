package com.example.project.facades;

import com.example.project.pojo.Customer;
import com.example.project.pojo.User;
import com.example.project.token.LoginToken;
import org.springframework.stereotype.Component;

/*
All the users (admin, airline,customer) have to past
function class -login- to enter the application.
 */
@Component
public class AnonymousFacade extends FacadeBase {
    //@param username and password. and return object -> facade / String
    public Object login(String username, String password) {
        var thisUser = userDAO.getUserByUsername(username);
        if (thisUser.userPassword.equals(password)) {
            switch (thisUser.userRole) {
                case 1 -> {
                    System.out.println("customer");
                    return new CustomerFacade(new LoginToken(
                            customerDAO.getCustomerByUsername(username).customerId,
                            customerDAO.getCustomerByUsername(username).firstName, "customer"));
                }
                case 2 -> {
                    System.out.println("admin");
                    return new AdministratorFacade(new LoginToken(
                            administratorDAO.getAdministratorByUsername(username).administratorId,
                            administratorDAO.getAdministratorByUsername(username).firstName, "admin"));
                }
                case 3 -> {
                    System.out.println("airline");
                    return new AirlineFacade(new LoginToken(
                            airlineCompanyDAO.getAirlineByUsername(username).airlineCompanyId,
                            airlineCompanyDAO.getAirlineByUsername(username).airlineCompanyName, "airline"));
                }
                default -> {
                    return "Error: user without role";
                }
            }
        }
        return "Error: wrong password";
    }
    //Adding new customer after creating a user.
    public void addCustomer(User user, Customer customer) {
        createNewUser(user);
        customerDAO.add(customer);
    }


}
