package Vinnsla;

public class Package {
    private Flight flightTo;
    private Flight flightFrom;
    private Hotel hotel;
    private DayTrip dayTrip;

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
}
