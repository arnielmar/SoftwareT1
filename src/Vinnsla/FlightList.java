package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Geymir lista af flugum og hefur aðferð til að leita
 * að flugum í gagnagrunni.
 *
 */
public class FlightList {
    private ObservableList<Flight> flights;
    private ListOfFlights listOfFlights;

    /**
     * Smíðar nýjan Observable List af Flight hlutum
     * og tengist við gagnagrunn af flugum.
     *
     */
    public FlightList() {
        this.flights = FXCollections.observableArrayList();
        listOfFlights = new ListOfFlights();
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
     * Leitar að flugum frá depart til dest á dagsetningu date
     * fyrir noOfPeople manns.
     *
     * @param dest - Áfangastaður
     * @param depart - Brottfararstaður
     * @param date - Dagsetning brottfarar
     * @param noOfPeople - Fjöldi manns
     * @return - ArrayList<Flight> results - listi af flugum sem passa við leit
     */
    public ArrayList<Flight> searchFlights(String depart, String dest, LocalDate date, int noOfPeople) {
        ArrayList<Flight> allFlights = listOfFlights.getListOfFlights();
        ArrayList<Flight> results = new ArrayList<>();
        for (Flight flight : allFlights) {
            if (flight.getDepartureLoc() == depart &&
                    flight.getArrivalLoc() == dest &&
                    flight.getFlightDate().equals(date) &&
                    flight.getCapacity() >= noOfPeople) {
                            results.add(flight);
            }

        }
        return results;
    }
}
