package Vinnsla;

/**
 * Klasi fyrir Package hluti.
 *
 */
public class Package implements Comparable<Package>{
    private Flight flightTo;
    private Flight flightFrom;
    private Hotel hotel;
    private DayTrip dayTrip;
    private int price;

    /**
     * Smiður fyrir Package hlut.
     *
     * @param flightTo - Flight hlutur til
     * @param flightFrom - Flight hlutur frá
     * @param hotel - Hotel hlutur
     * @param dayTrip - DayTrip hlutur
     */
    public Package(Flight flightTo, Flight flightFrom, Hotel hotel, DayTrip dayTrip) {
        this.flightTo = flightTo;
        this.flightFrom = flightFrom;
        this.hotel = hotel;
        this.dayTrip = dayTrip;
        this.price = (int)((flightTo.getPrice()+flightFrom.getPrice()+hotel.getPrice()+dayTrip.getPrice())*0.9);
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
        return "From " + flightTo.getDepartureLoc() + " to " + flightTo.getArrivalLoc() + " on " + flightTo.getFlightDate() +" back "+flightFrom.getFlightDate()+", at the hotel "+ hotel.getHotelName()+ " with daytrip "+dayTrip.getType()+" on "+dayTrip.getDateAndTime()+". Price: "+price+"kr";
    }

    @Override
    public int compareTo(Package that)
    {
        return Integer.compare(this.price, that.price);
    }
}
