import java.util.Date;

public class Flight {
    private String departureLoc;
    private String arrivalLoc;
    private Date flightDate;
    private int passengerNumber;
    private String[] seats;

    /**
     *
     * @param depart
     * @param arrival
     * @param date
     * @param nr
     * @param seat
     */
    void init (String depart, String arrival, Date date, int nr, String[] seats )
    {
        this.departureLoc = depart;
        this.arrivalLoc = arrival;
        this.flightDate = date;
        this.passengerNumber = nr;
        this.seats = seats;
    }

    public String getDepartureLoc() {
        return departureLoc;
    }

    public String getArrivalLoc() {
        return arrivalLoc;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public String[] getSeats() {
        return seats;
    }
}