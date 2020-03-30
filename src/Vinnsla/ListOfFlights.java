package Vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListOfFlights {

    ArrayList<Flight> listiAfFlugum;

    public ListOfFlights() {
        listiAfFlugum = new ArrayList<Flight>();
        Flight flight1 = new Flight("Reykjavík", "París", LocalDate.now(), 100);
        Flight flight2 = new Flight("París", "Reykjavík", LocalDate.now(), 50);
        Flight flight3 = new Flight("Reykjavík", "París", LocalDate.of(2020, 4, 16), 2);
        listiAfFlugum.add(flight1);
        listiAfFlugum.add(flight2);
        listiAfFlugum.add(flight3);
    }

    public ArrayList<Flight> getListiAfFlugum() {
        return listiAfFlugum;
    }
}
