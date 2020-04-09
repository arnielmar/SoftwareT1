package Vinnsla;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Mock object af DayTrip gangagrunni.
 */
public class ListOfDayTrips {

    ArrayList<DayTrip> listofDayTrips;

    /**
     * Smíðar 10 Daytrip mock object til að setjaí listann.
     */
    public ListOfDayTrips()
    {
            listofDayTrips = new ArrayList<DayTrip>();

            DayTrip dt1 = new DayTrip("Reykjavík", LocalDateTime.now(), 3, 4, "History", 30000);
            DayTrip dt2 = new DayTrip("París", LocalDateTime.of(2020, 4, 26, 12, 00), 6, 4, "Activity", 15000);
            DayTrip dt3 = new DayTrip("London", LocalDateTime.now(), 2, 6, "Gourmet", 17000);
            DayTrip dt4 = new DayTrip("Reykjavík", LocalDateTime.now(), 6, 10, "Gourmet", 20000);
            DayTrip dt5 = new DayTrip("Copenhagen", LocalDateTime.of(2020, 5, 28, 12, 00), 10, 2, "Activity", 7000);
            DayTrip dt6 = new DayTrip("París", LocalDateTime.now(), 5, 1, "Gourmet", 40000);
            DayTrip dt7 = new DayTrip("London", LocalDateTime.of(2020, 5, 28, 12, 00), 4, 5, "History", 30000);
            DayTrip dt8 = new DayTrip("Copenhagen", LocalDateTime.of(2020, 5, 28, 12, 00), 9, 3, "Activity", 12000);
            DayTrip dt9 = new DayTrip("París", LocalDateTime.of(2020, 5, 28, 12, 00), 5, 2, "Gourmet", 6000);
            DayTrip dt10 = new DayTrip("Copenhagen", LocalDateTime.now(), 1, 4, "History", 3000);

            listofDayTrips.add(dt1);
            listofDayTrips.add(dt2);
            listofDayTrips.add(dt3);
            listofDayTrips.add(dt4);
            listofDayTrips.add(dt5);
            listofDayTrips.add(dt6);
            listofDayTrips.add(dt7);
            listofDayTrips.add(dt8);
            listofDayTrips.add(dt9);
            listofDayTrips.add(dt10);
    }

    /**
     * getter sem skilar öllum gagnagrunninum.
     * @return
     */
    public ArrayList<DayTrip> getListofDayTrips()
    {
        return listofDayTrips;
    }

    /**
     * Getter sem skilar öllum stadsetningum.
     * @return - ArrayList<String> - listi með öllum stadsetningum
     */
    public ArrayList<String> getPlaces() {
        ArrayList<String> places = new ArrayList<String>(Arrays.asList("Reykjavík", "París", "London", "Copenhagen"));
        return places;
    }
}
