package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlightList {
    private ObservableList<Flight> flights;
    private ListOfFlights listOfFlights = new ListOfFlights();

    /**
     * Smíðar nýjan Observable List af Flight hlutum.
     */
    public FlightList() {
        this.flights = FXCollections.observableArrayList();
    }

    /**
     * Skilar flugi sem er númer index á lista.
     *
     * @param index - nr. flugs
     * @return Flight - flug nr. index í lista
     */
    public Flight getFlight(int index) {
        return (Flight)flights.get(index);
    }

    /**
     * Skilar öllum lista af flugum.
     *
     * @return - lista af Flight hlutum
     */
    public ObservableList<Flight> getAllFlights() {
        return flights;
    }

    /**
     * Eyðir flugi nr. index úr lista.
     *
     * @param index - nr. flugs
     */
    public void deleteFlight(int index) {
        flights.remove(index);
    }

    /**
     * Eyðir öllum flugum úr lista.
     *
     */
    public void deleteAllFlights() {
        flights.removeAll();
    }

    /**
     * Bætir flugi flight við á lista.
     *
     * @param flight - Flight hlutur sem bæta á við í lista
     */
    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    /**
     * Bætir öllum flugum á listi við listann af flugum.
     *
     * @param listi - listi af flugum
     */
    public void addFlights(ObservableList<Flight> listi) {
        flights.addAll(listi);
    }

    /**
     * Leitar að flugum frá depart til dest á dagsetningu from,
     * og einnig að flugum frá dest til depart á dagsetningu to
     * fyrir noOfPeople manns.
     * Skilar ArrayList<F1>.
     *
     * @param dest - Áfangastaður
     * @param depart - Brottfararstaður
     * @param from - Dagsetning brottfarar
     * @param to - Dagsetning komu
     * @param noOfPeople - Fjöldi manns
     */
    public void searchFlightsRoundWay(String depart, String dest, LocalDate from, LocalDate to, int noOfPeople) {
        flights.removeAll();
        ArrayList<Flight> allFlights = listOfFlights.getListiAfFlugum();
        for (Flight flight : allFlights) {
            if (flight.getDepartureLoc() == depart &&
                flight.getArrivalLoc() == dest &&
                flight.getFlightDate() == from &&
                flight.getCapacity() >= noOfPeople) {
                flights.add(flight);
            }
        }
    }

    /**
     * Leitar að flugum frá depart til dest á dagsetningu date
     * fyrir noOfPeople manns.
     * Skilar ArrayList<F1>.
     *
     * @param dest - Áfangastaður
     * @param depart - Brottfararstaður
     * @param date - Dagsetning brottfarar
     * @param noOfPeople - Fjöldi manns
     */
    public void searchFlightsOneWay(String depart, String dest, LocalDate date, int noOfPeople) {
        flights.removeAll();
        ArrayList<Flight> allFlights = listOfFlights.getListiAfFlugum();
        for (Flight flight : allFlights) {
            if (flight.getDepartureLoc() == depart &&
                    flight.getArrivalLoc() == dest &&
                    flight.getFlightDate() == date &&
                    flight.getCapacity() >= noOfPeople) {
                flights.add(flight);
            }
        }
    }
}
