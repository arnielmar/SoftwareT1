package Vinnsla;

import java.util.Date;


public class Hotel {
    private String location;
    private Date arrivalDate;
    private Date departDate;
    private int customerNumber;
    private String roomType;

    /**
     *
     * @param loc
     * @param arrive
     * @param depart
     * @param number
     * @param type
     */
    public void Hotel(String loc, Date arrive, Date depart, int number, String type){
        this.location = loc;
        this. arrivalDate = arrive;
        this.departDate = depart;
        this.customerNumber = number;
        this.roomType = type;
    }

    public String getLocation() {
        return location;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getRoomType() {
        return roomType;
    }
}
