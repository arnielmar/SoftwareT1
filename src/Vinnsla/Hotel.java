package Vinnsla;

import java.time.LocalDate;


public class Hotel {
    private String location;
    private LocalDate arrivalDate;
    private LocalDate departDate;
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
    public void Hotel(String loc, LocalDate arrive, LocalDate depart, int number, String type){
        this.location = loc;
        this. arrivalDate = arrive;
        this.departDate = depart;
        this.customerNumber = number;
        this.roomType = type;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartDate() {
        return departDate;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getRoomType() {
        return roomType;
    }
}
