package dao;

import pojo.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements DAO {

    Connection_Statement cs = new Connection_Statement();
    List<Ticket> ticketList = new ArrayList<>();

    @Override
    public Object get(int id) {
        Ticket ticket = null;
        try {
            ResultSet result = cs.statement.executeQuery("select * from Tickets where Ticket_Id=" + id);
            result.next();
            ticket = new Ticket(result.getLong("Ticket_Id"),
                    result.getLong("Flight_Id"),
                    result.getLong("Customer_Id"));
            cs.closeCSR(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;

    }

    @Override
    public List<Ticket> getAll() {
        try {
            ResultSet result = cs.statement.executeQuery("select * from Tickets");
            while (result.next()) {
                ticketList.add(new Ticket(result.getLong("Ticket_Id"),
                        result.getLong("Flight_Id"),
                        result.getLong("Customer_Id")));
            }
            cs.closeCSR(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    @Override
    public void add(Object o) {
        if (o instanceof Ticket ticket) {
            try {
                cs.statement.executeUpdate("insert into Tickets(Flight_Id,Customer_Id)" +
                        "values" + "(" + ticket.flightId + "," + ticket.customerId + ")");
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof Ticket ticket) {
            try {
                cs.statement.executeUpdate("delete from Tickets where Ticket_Id =" +
                        ticket.ticketId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Ticket ticket) {
            try {
                cs.statement.executeUpdate("update Tickets set Flight_Id=" + ticket.flightId + "," +
                        "Customer_Id=" + ticket.customerId + "where Ticket_Id =" + ticket.ticketId);
                cs.closeCS();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Ticket> getTicketsByCustomer(long customer_id) {
        List<Ticket> get_tickets_by_customer = new ArrayList<>();
        try {
            ResultSet result = cs.statement.executeQuery("select * from get_tickets_by_customer" + "(" + customer_id + ")");
            while (result.next()) {
                get_tickets_by_customer.add(new Ticket(result.getLong("Ticket_Id"),
                        result.getLong("Flight_Id"),
                        result.getLong("Customer_Id")));
                cs.closeCSR(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return get_tickets_by_customer;
    }


}
