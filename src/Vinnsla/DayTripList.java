package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class DayTripList {
    private ObservableList<DayTrip> dayTrips;

    /**
     * Smíðar nýjan Observable List af DayTrip hlutum.
     */
    public DayTripList() {
        this.dayTrips = FXCollections.observableArrayList();
    }

    /**
     * Skilar day trip sem er númer index á lista.
     *
     * @param index - nr. day trips
     * @return Flight - day trip nr. index í lista
     */
    public DayTrip getDayTrip(int index) {
        return (DayTrip)dayTrips.get(index);
    }

    /**
     * Skilar öllum lista af day trips.
     *
     * @return - lista af DayTrip hlutum
     */
    public ObservableList<DayTrip> getAllDayTrips() {
        return dayTrips;
    }

    /**
     * Eyðir day trip nr. index úr lista.
     *
     * @param index - nr. day trips
     */
    public void deleteDayTrip(int index) {
        dayTrips.remove(index);
    }

    /**
     * Bætir day trip dayTrip við á lista.
     *
     * @param dayTrip - DayTrip hlutur sem bæta á við í lista
     */
    public void addDayTrip(DayTrip dayTrip) {
        dayTrips.add(dayTrip);
    }
}