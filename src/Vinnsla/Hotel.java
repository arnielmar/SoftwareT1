package Vinnsla;

import java.time.LocalDate;


public class Hotel implements Comparable<Hotel>{
    private String hotelName;
    private String location;
    private LocalDate arrivalDate;
    private LocalDate departDate;
    private int customerNumber;
    private String[] roomType;
    private int price;

    /**
     *
     * @param loc
     * @param arrive
     * @param depart
     * @param number
     * @param type
     */
    public Hotel(String name, String loc, LocalDate arrive, LocalDate depart, int number, String[] type, int price){
        this.hotelName = name;
        this.location = loc;
        this.arrivalDate = arrive;
        this.departDate = depart;
        this.customerNumber = number;
        this.roomType= type;
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
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

    public String[] getRoomType() {
        return roomType;
    }

    public int getPrice(){
        return price;
    }

    public int getRoomTypeNumber(String room){
        if(room.equals("single")){
            return 1;
        } else if(room.equals("double")) {
            return 2;
        } else if(room.equals("penthouse")){
            return 4;
        } else {
            return 0;
        }
    }


    @Override
    public String toString() {
        return hotelName + " in " + location + " from " + arrivalDate + " to " + departDate + ". Price: " + price + " -kr.";
    }

    @Override
    public int compareTo(Hotel that) {
        return Integer.compare(this.price, that.price);
    }

}
