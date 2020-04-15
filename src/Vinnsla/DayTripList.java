package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Geymir lista af DayTrips og hefur aðferð til að leita í gagnagrunni að DayTrip.
 *
 */
public class DayTripList {
    private ObservableList<DayTrip> dayTrips;
    private ListOfDayTrips listOfDayTrips;

    /**
     * Smíðar nýjan Observable List af DayTrip hlutum.
     *
     */
    public DayTripList() {
        this.dayTrips = FXCollections.observableArrayList();
        listOfDayTrips = new ListOfDayTrips();
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

    /**
     * Leitar að day trips á staðsetningu dest á dagsetningu date
     * fyrir noOfPeople manns (með pláss fyrir noOfPeople manns í dayTrip-inu).
     * Skilar ArrayList<DayTrip>.
     *
     * @param dest - Áfangastaður
     * @param date - Dagsetning day trips
     * @param noOfPeople - Fjöldi manns
     */
    public ArrayList<DayTrip> searchDayTrips(String dest, LocalDate date, int noOfPeople) {
        ArrayList<DayTrip> allDT = listOfDayTrips.getListofDayTrips();
        ArrayList<DayTrip> results = new ArrayList<DayTrip>();

        int i;
        for (i = 0; i < allDT.size(); i++)
        {
            if (allDT.get(i).getLocation().equals(dest) &&
                    compareDaTandD(allDT.get(i).getDateAndTime(), date) &&
                    noOfPeople <= allDT.get(i).getNoOfPersons())
            {
                results.add(allDT.get(i));
            }
        }

        return results;
    }

    /**
     *
     * @param DaT   LocalDateTime
     * @param D     LocalDate
     * @return  true if same date false else
     */
    private boolean compareDaTandD(LocalDateTime DaT, LocalDate D) {
        if (DaT.getYear() == D.getYear() && DaT.getDayOfYear() == D.getDayOfYear()) {
            return true;
        }
        return false;
    }
}