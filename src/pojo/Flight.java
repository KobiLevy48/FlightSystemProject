package com.example.project.pojo;

import java.sql.Timestamp;


public class Flight implements POJO{
    public long flightId;
    public long airlineCompanyId;
    public int originCountryId;
    public int destinationCountryId;
    public Timestamp departureTime;
    public Timestamp landingTime;
    public int remainingTickets;

    public Flight(long flightId, long airlineCompanyId, int originCountryId, int destinationCountryId,
                  Timestamp departureTime, Timestamp landingTime, int remainingTickets) {
        this.flightId = flightId;
        this.airlineCompanyId = airlineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", airlineCompanyId=" + airlineCompanyId +
                ", originCountryId=" + originCountryId +
                ", destinationCountryId=" + destinationCountryId +
                ", departureTime=" + departureTime +
                ", landingTime=" + landingTime +
                ", remainingTickets=" + remainingTickets +
                '}'+"\n";
    }
}
