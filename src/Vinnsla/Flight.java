package Vinnsla;

import java.time.LocalDate;

/**
 * Mock Object af Flight hlutum.
 *
 */
public class Flight implements Comparable<Flight> {
    private String departureLoc;
    private String arrivalLoc;
    private LocalDate flightDate;
    private int capacity;
    private int price;


    /**
     * Smiður fyrir Flight hlut.
     *
     * @param depart - Brottfararstaður
     * @param arrival - Áfangastaður
     * @param date - Dagsetning flugs
     * @param nr - Fjöldi lausra sæta
     * @param price - Verð flugs
     */
    public Flight (String depart, String arrival, LocalDate date, int nr, int price) {
        this.departureLoc = depart;
        this.arrivalLoc = arrival;
        this.flightDate = date;
        this.capacity = nr;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "From " + departureLoc + " to " + arrivalLoc + " on " + flightDate + ". Price: " + price + "kr.";
    }

    @Override
    public int compareTo(Flight that) {
        return Integer.compare(this.price, that.price);
    }
}