package com.example.project.pojo;

public class Ticket implements POJO{
    public long ticketId;
    public long flightId;
    public long customerId;

    public Ticket(long ticketId, long flightId, long customerId) {
        this.ticketId = ticketId;
        this.flightId = flightId;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", flightId=" + flightId +
                ", customerId=" + customerId +
                '}'+"\n";
    }
}
