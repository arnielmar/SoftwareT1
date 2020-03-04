package Vinnsla;

import java.util.Date;

public class DayTrip {
    private String location;
    private Date date;
    private Date time;          // eitthvað annað en Date samt
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
    public DayTrip(String location, Date date, Date time, int length, int noOfPersons, String type) {
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

    public Date getDate() {
        return date;
    }

    public Date getTime() {
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
