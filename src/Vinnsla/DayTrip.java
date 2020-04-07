package Vinnsla;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Mock object af DayTrip hlutum.
 *
 */
public class DayTrip implements Comparable<DayTrip> {
    private String location;
    private LocalDateTime dateAndTime;
    private int length;
    private int noOfPersons;
    private String type;
    private int price;

    /**
     * Smiður fyrir DayTrip hlut.
     *
     * @param location - Staðsetning á day trip
     * @param dateAndTime - Dagur og Tími day trip
     * @param length - Lengd day trip í klst
     * @param noOfPersons - Fjöldi manns
     * @param type - Tegund day trip
     */
    public DayTrip(String location,LocalDateTime dateAndTime, int length, int noOfPersons, String type, int price) {
        this.location = location;
        this.dateAndTime = dateAndTime;
        this.length = length;
        this.noOfPersons = noOfPersons;
        this.type = type;
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public int getLength() {
        return length;
    }

    public int getNoOfPersons() {
        return noOfPersons;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type.equals("Activity"))
        {
            if (location.equals("Reykjavík"))
            {
                return "Whale watching in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("London"))
            {
                return "The Zoo in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("París"))
            {
                return "The Eiffel Tower in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("Copenhagen")) {
                return "Tívoli in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            }
        } else if (type.equals("Gourmet"))
        {
            if (location.equals("Reykjavík"))
            {
                return "Traditonal 3 course Icelandic meal in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("London"))
            {
                return "The fish'N'chip experience in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("París"))
            {
                return "Wine tasting in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("Copenhagen")) {
                return "Beer bananza! in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            }
        } else if (type.equals("History"))
        {
            if (location.equals("Reykjavík"))
            {
                return "Aarbaejarmuseum (Árbæjarsafn) in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("London"))
            {
                return "The British museum in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("París"))
            {
                return "Mime History museum in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            } else if (location.equals("Copenhagen")) {
                return "The history of Legos museum in " + location + " at " + dateAndTime.toLocalDate().toString() + " Price: " + price;
            }
        }
        return "DayTrip not found";
    }

    @Override
    public int compareTo(DayTrip that)
    {
        return Integer.compare(this.price, that.price);
    }
}
