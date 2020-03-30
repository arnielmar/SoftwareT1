package Vinnsla;

import java.time.LocalDate;

public class Flight {
    private String departureLoc;
    private String arrivalLoc;
    private LocalDate flightDate;
    private int capacity;


    /**
     *
     * @param depart
     * @param arrival
     * @param date
     * @param nr
     */
    public Flight (String depart, String arrival, LocalDate date, int nr) {
        this.departureLoc = depart;
        this.arrivalLoc = arrival;
        this.flightDate = date;
        this.capacity = nr;
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

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "From " + departureLoc + " to " + arrivalLoc + " on " + flightDate;
    }
}