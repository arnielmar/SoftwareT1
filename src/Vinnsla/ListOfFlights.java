package Vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mock Object af gagnagrunni fyrir flug.
 *
 */
public class ListOfFlights {

    ArrayList<Flight> listOfFlights;

    /**
     * Smíðar mock gagnagrunn með 10 Flight hlutum.
     *
     */
    public ListOfFlights() {
        listOfFlights = new ArrayList<Flight>();
        Flight flight1 = new Flight("Reykjavík", "París", LocalDate.now(), 100, 35000);
        Flight flight2 = new Flight("París", "Reykjavík", LocalDate.now(), 50, 29000);
        Flight flight3 = new Flight("París", "Reykjavík", LocalDate.of(2020, 4, 28), 2, 23000);
        Flight flight4 = new Flight("Reykjavík", "London", LocalDate.now(), 5, 18000);
        Flight flight5 = new Flight("London", "Reykjavík", LocalDate.of(2020, 5, 14), 36, 24000);
        Flight flight6 = new Flight("London", "París", LocalDate.now(), 80, 15000);
        Flight flight7 = new Flight("París", "London", LocalDate.now(), 25, 12000);
        Flight flight8 = new Flight("París", "London", LocalDate.of(2020, 4, 15), 10, 21000);
        Flight flight9 = new Flight("Reykjavík", "Copenhagen", LocalDate.now(), 20, 12000);
        Flight flight10 = new Flight("Copenhagen", "Reykjavík", LocalDate.of(2020, 5, 30), 15, 30000);
        listOfFlights.add(flight1);
        listOfFlights.add(flight2);
        listOfFlights.add(flight3);
        listOfFlights.add(flight4);
        listOfFlights.add(flight5);
        listOfFlights.add(flight6);
        listOfFlights.add(flight7);
        listOfFlights.add(flight8);
        listOfFlights.add(flight9);
        listOfFlights.add(flight10);
    }

    /**
     * Getter sem skilar öllum gagngagrunni af flugum.
     *
     * @return - ArrayList<Flight> - listi með gagnagrunninum af flugum
     */
    public ArrayList<Flight> getListOfFlights() {
        return listOfFlights;
    }

    /**
     * Getter sem skilar öllum áfangastöðum.
     *
     * @return - ArrayList<String> - listi með öllum áfangastöðunum
     */
    public ArrayList<String> getPlaces() {
        ArrayList<String> places = new ArrayList<String>(Arrays.asList("Reykjavík", "París", "London", "Copenhagen"));
        return places;
    }
}
