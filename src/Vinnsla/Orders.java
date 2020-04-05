package Vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class Orders {
    private ArrayList<Flight> flights;
    private Vector<Flight> Flights;
    private Vector<Hotel> Hotels;
    //todo daytours[]
    //todo hotels[]

    public Orders() {
        flights = new ArrayList<Flight>();
        Flights = new Vector<Flight>();
        Hotels = new Vector<Hotel>();
    }


    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addHotel(Hotel hotel){
        Hotels.add(hotel);
    }

    /*public void makeDummyFlights() {
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
    }*/

    public void makeDummyHotels() {
        String[] tmpstring = new String[1];
        int i;
        for ( i = 0; i < 10; i++ )
        {
            tmpstring[0] = ""+i;
            LocalDate tmpDate = LocalDate.now();
            //Hotel tmpHotel = new Hotel( "Reykjavík " + i, tmpDate, tmpDate, i, "penthouse");
            //addHotel(tmpHotel);
        }
        for ( i = 0; i < 10; i++ )
        {
            Hotel tmpHotel = Hotels.get(i);
            System.out.println(tmpHotel.getLocation());
        }
    }

}
