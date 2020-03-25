package Vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;

public class mockupFlights {
    ArrayList<mockupFlight> flights = new ArrayList<mockupFlight>();

    public mockupFlights() {
        LocalDate now = LocalDate.now();
        LocalDate notNow = LocalDate.of(2020,4,15);
        flights.add(new mockupFlight("Rey","Ak",now,50));
        flights.add(new mockupFlight("Ak","Rey",notNow,40));
        flights.add(new mockupFlight("Rey","London",now,50));
        flights.add(new mockupFlight("London","Rey",notNow,30));
        flights.add(new mockupFlight("London","Rey",notNow,50));
    }

    public ArrayList<mockupFlight> search(String departure, String arrival, LocalDate date, int nrOfPassengers) {
        ArrayList<mockupFlight> tmpList = new ArrayList<mockupFlight>();
        int i;
        for (i = 0; i < flights.size(); i++) {
            mockupFlight theflight = flights.get(i);
            if (theflight.getDepartureLoc() == departure &&
                    theflight.getArrivalLoc() == arrival &&
                    theflight.getFlightDate() == date &&
                    theflight.getSeats() >= nrOfPassengers) {
                tmpList.add(theflight);
            }
        }
        return tmpList;
    }
}
