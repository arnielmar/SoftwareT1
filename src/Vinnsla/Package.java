package Vinnsla;

public class Package implements Comparable<Package>{
    private Flight flightTo;
    private Flight flightFrom;
    private Hotel hotel;
    private DayTrip dayTrip;
    private int price;

    /**
     *
     * @param flightTo
     * @param flightFrom
     * @param hotel
     * @param dayTrip
     */
    public Package(Flight flightTo, Flight flightFrom, Hotel hotel, DayTrip dayTrip) {
        this.flightTo = flightTo;
        this.flightFrom = flightFrom;
        this.hotel = hotel;
        this.dayTrip = dayTrip;
        this.price = (int)((flightTo.getPrice()+flightFrom.getPrice()+hotel.getPrice())*0.9);
    }

    public Flight getFlightTo() {
        return flightTo;
    }

    public Flight getFlightFrom() {
        return flightFrom;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public DayTrip getDayTrip() {
        return dayTrip;
    }

    public int getPrice() {return price;}

    @Override
    public String toString() {
        return "Frá " + flightTo.getDepartureLoc() + " til " + flightTo.getArrivalLoc() + " á " + flightTo.getFlightDate() +" til "+flightFrom.getFlightDate()+", á hótelinu "+ hotel.getHotelName()+ " með dagferðina "+dayTrip.getType()+" á deginum "+dayTrip.getDateAndTime()+", verð: "+price+"kr";
    }

    @Override
    public int compareTo(Package that)
    {
        return Integer.compare(this.price, that.price);
    }
}
