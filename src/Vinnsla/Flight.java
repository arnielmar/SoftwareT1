package Vinnsla;

import java.time.LocalDate;

public class Flight {
    private String departureLoc;
    private String arrivalLoc;
    private LocalDate flightDate;
    private int passengerNumber;
    private String[] seats;


    /**
     *
     * @param depart //departure location
     * @param arrival
     * @param date
     * @param nr
     * @param seats
     */
    public Flight (String depart, String arrival, LocalDate date, int nr, String[] seats) {
        this.departureLoc = depart;
        this.arrivalLoc = arrival;
        this.flightDate = date;
        this.passengerNumber = nr;
        this.seats = seats;
    }

    public String getDepartureLoc() {
        return departureLoc;
    }

    public String getArrivalLoc() {
        return arrivalLoc;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public String[] getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "From " + departureLoc + " to " + arrivalLoc + " on " + flightDate;
    }
}