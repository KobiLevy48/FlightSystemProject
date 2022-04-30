package com.example.project.controller;

import com.example.project.facades.CustomerFacade;
import com.example.project.pojo.Customer;
import com.example.project.pojo.Ticket;
import com.example.project.token.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerFacade customerFacade = new CustomerFacade(new LoginToken());

    @PutMapping("/update")
    public void updateCustomer(@RequestBody Customer customer){
        customerFacade.updateCustomer(customer);
    }

    @PostMapping("/add_ticket")
    public void addTicket(@RequestBody Ticket ticket){
        customerFacade.addTicket(ticket);
    }

    @DeleteMapping("/remove_ticket/{id}")
    public void removeTicket(@PathVariable int id){
        customerFacade.removeTicket(customerFacade.getMyTickets().get(id));
    }

    @GetMapping("/my_tickets")
    public List<Ticket> getMyTickets(){
        return customerFacade.getMyTickets();
    }
}
