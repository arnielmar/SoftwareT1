package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class FlightList {
    private ObservableList<mockupFlight> flights;

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
    public mockupFlight getFlight(int index) {
        return (mockupFlight)flights.get(index);
    }

    /**
     * Skilar öllum lista af flugum.
     *
     * @return - lista af Flight hlutum
     */
    public ObservableList<mockupFlight> getAllFlights() {
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
     * Bætir flugi flight við á lista.
     *
     * @param flight - Flight hlutur sem bæta á við í lista
     */
    public void addFlight(mockupFlight flight) {
        flights.add(flight);
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
    public void searchFlightsRoundWay(String dest, String depart, LocalDate from, LocalDate to, int noOfPeople) {
        //TODO
        mockupFlights mFlights = new mockupFlights();
        ObservableList<mockupFlight> flights2;
        flights = FXCollections.observableArrayList(mFlights.search(depart, dest, from, noOfPeople));
        flights2 = FXCollections.observableArrayList(mFlights.search(dest, depart, to, noOfPeople));
        flights.addAll(flights2);

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
    public void searchFlightsOneWay(String dest, String depart, LocalDate date, int noOfPeople) {
        //TODO
        mockupFlights mFlights = new mockupFlights();
        flights = FXCollections.observableArrayList(mFlights.search(depart, dest, date, noOfPeople));
    }
}
