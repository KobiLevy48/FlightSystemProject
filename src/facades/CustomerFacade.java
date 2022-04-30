package com.example.project.facades;


import com.example.project.pojo.Customer;
import com.example.project.pojo.Flight;
import com.example.project.pojo.Ticket;
import com.example.project.token.LoginToken;
import com.sun.istack.NotNull;



import java.util.List;

public class CustomerFacade extends AnonymousFacade {
    private final LoginToken loginToken;
    //Use dao function
    public CustomerFacade(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public void updateCustomer(@NotNull Customer customer) {
        if (checkToken(customer.customerId)) customerDAO.update(customer);
        else {
            System.out.println("Error: you can't update another customer");
        }
    }

    public void addTicket(@NotNull Ticket ticket) {
        if (checkToken(ticket.customerId)) {
            ticketDAO.add(ticket);
           Flight flight = (Flight)flightDAO.get((int) ticket.flightId);
           flight.remainingTickets--;
        }
        else {
            System.out.println("Error: you can't add ticket for another customer");
        }
    }

    public void removeTicket(@NotNull Ticket ticket) {
        if (checkToken(ticket.customerId)){
            ticketDAO.remove(ticket);
            Flight flight = (Flight)flightDAO.get((int)ticket.flightId);
            flight.remainingTickets++;
        }
        else {
            System.out.println("Error: you can't remove ticket for another customer");
        }
    }

    public List<Ticket> getMyTickets() {
        return ticketDAO.getTicketsByCustomer(loginToken.getId());
    }


    private boolean checkToken(long id) {
        return id == loginToken.getId();
    }
}
