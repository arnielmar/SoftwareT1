package Vinnsla;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DayTrip {
    private String location;
    private LocalDate date;
    private LocalDateTime time;
    private int length;
    private int noOfPersons;
    private String type;

    /**
     * Smiður fyrir tilvik af hlutnum DayTrip.
     *
     * @param location - Staðsetning á day trip
     * @param date - Dagsetning day trip
     * @param time - Tími day trip
     * @param length - Lengd day trip
     * @param noOfPersons - Fjöldi manns
     * @param type - Tegund day trip
     */
    public DayTrip(String location, LocalDate date, LocalDateTime time, int length, int noOfPersons, String type) {
        this.location = location;
        this.date = date;
        this.time = time;
        this.length = length;
        this.noOfPersons = noOfPersons;
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getTime() {
        return time;
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
}
