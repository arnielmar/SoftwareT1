package Vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mock Object af gagnagrunni fyrir flug.
 *
 */
public class ListOfHotels {
    public static ArrayList<Hotel> listOfHotels;

    /**
     * Smíðar mock gagnagrunn með 10 Flight hlutum.
     *
     */
    public ListOfHotels() {
        listOfHotels = new ArrayList<Hotel>();
        String[] types = {"penthouse", "single", "double"};
        Hotel hotel1 = new Hotel("The Mandala Hotel", "London", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 2, types);
        //Hotel hotel2 = new Hotel("The Mandala Hotel", "London", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 1, "single");
        types = new String[]{"single", "double"};
        Hotel hotel3 = new Hotel("Radisson Hotel", "Reykjavík", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 2, types);
        Hotel hotel4 = new Hotel("Fosshótel", "Reykjavík", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 2, types);
        types = new String[]{"single", "penthouse"};
        Hotel hotel5 = new Hotel("Le Pirois", "París", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 2, types);
        types = new String[]{"single", "double"};
        Hotel hotel6 = new Hotel("Grand Hotel Saint Michel", "París", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 1, types);
        //Hotel hotel7 = new Hotel("Grand Hotel Saint Michel", "París", LocalDate.now(), LocalDate.of(2020,4,25), 2, types);
        Hotel hotel8 = new Hotel("Absalon Hotel", "Copenhagen", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 1, types);
        Hotel hotel9 = new Hotel("Copenhagen Marriott Hotel", "Copenhagen", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 2, types);
        Hotel hotel10 = new Hotel("Lux Eleven Berlin-Mitte", "London", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 1, types);
        listOfHotels.add(hotel1);
        //listOfHotels.add(hotel2);
        listOfHotels.add(hotel3);
        listOfHotels.add(hotel4);
        listOfHotels.add(hotel5);
        listOfHotels.add(hotel6);
        //listOfHotels.add(hotel7);
        listOfHotels.add(hotel8);
        listOfHotels.add(hotel9);
        listOfHotels.add(hotel10);
    }

    /**
     * Getter sem skilar öllum gagngagrunni af flugum.
     *
     * @return - ArrayList<Flight> - listi með gagnagrunninum af flugum
     */
    public static ArrayList<Hotel> getListOfHotels() {
        return listOfHotels;
    }

    /**
     * Getter sem skilar öllum áfangastöðum.
     *
     * @return - ArrayList<String> - listi með öllum áfangastöðunum
     */
    public ArrayList<String> getPlaces() {
        ArrayList<String> places = new ArrayList<String>(Arrays.asList("Reykjavík", "París", "London", "Copenhagen"));
        return places;
    }
}