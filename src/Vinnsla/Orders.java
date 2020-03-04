package Vinnsla;

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

}
