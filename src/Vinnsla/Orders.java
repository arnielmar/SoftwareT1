package Vinnsla;

import java.time.LocalDate;
import java.util.Vector;

public class Orders {
    private Vector<Flight> Flights;
    //todo daytours[]
    //todo hotels[]

    public Orders(  ) {
        Flights = new Vector<Flight>();
    }


    public void addFlight(Flight flight) {
        Flights.add(flight);
    }

    public void makeDummyFlights() {
        String[] tmpstring = new String[1];
        int i;
        for ( i = 0; i < 10; i++ )
        {
            tmpstring[0] = ""+i;
            LocalDate tmpDate = new LocalDate((i,i,i);
            Flight tmpFlight = new Flight( ""+i, ""+i, tmpDate, i, tmpstring);
            addFlight(tmpFlight);
        }
    }

}
