package Vinnsla;

import java.time.LocalDate;

public class mockupFlight {
    private String departureLoc;
    private String arrivalLoc;
    private LocalDate flightDate;
    private int seats;


    /**
     *
     * @param depart //departure location
     * @param arrival
     * @param date
     * @param seats
     */
    public mockupFlight (String depart, String arrival, LocalDate date, int seats) {
        this.departureLoc = depart;
        this.arrivalLoc = arrival;
        this.flightDate = date;
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

    public int getSeats() {
        return seats;
    }

}