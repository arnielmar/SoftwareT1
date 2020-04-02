package Vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;

public class Orders {
    private ArrayList<Flight> flights;
    //todo daytours[]
    //todo hotels[]

    public Orders(  ) {
        flights = new ArrayList<Flight>();
    }


    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void makeDummyFlights() {
        String[] tmpstring = new String[1];
        int i;
        for ( i = 0; i < 10; i++ )
        {
            tmpstring[0] = ""+i;
            LocalDate tmpDate = LocalDate.now();
            Flight tmpFlight = new Flight( ""+i, ""+i, tmpDate, i);
            addFlight(tmpFlight);
        }
        for ( i = 0; i < 10; i++ )
        {
            Flight tmpFlight = flights.get(i);
            System.out.println(tmpFlight.getArrivalLoc());
        }
    }

}
