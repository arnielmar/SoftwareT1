package Vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mock Object af gagnagrunni fyrir hótel.
 *
 */
public class ListOfHotels {
    public static ArrayList<Hotel> listOfHotels;

    /**
     * Smíðar mock gagnagrunn með 10 Hotel hlutum.
     *
     */
    public ListOfHotels() {
        listOfHotels = new ArrayList<Hotel>();
        String[] types = {"penthouse", "single", "double"};
        Hotel hotel1 = new Hotel("The Mandala Hotel", "London", LocalDate.now(), LocalDate.of(2020,4,28), 2, types, 45000);
        //Hotel hotel2 = new Hotel("The Mandala Hotel", "London", LocalDate.of(2020,4,17), LocalDate.of(2020,4,25), 1, "single");
        types = new String[]{"single", "double"};
        Hotel hotel3 = new Hotel("Radisson Hotel", "Reykjavík", LocalDate.now(), LocalDate.of(2020,4,28), 2, types, 30000);
        Hotel hotel4 = new Hotel("Fosshótel", "Reykjavík", LocalDate.now(), LocalDate.of(2020,4,28), 2, types, 65000);
        types = new String[]{"single", "penthouse"};
        Hotel hotel5 = new Hotel("Le Pirois", "París", LocalDate.now(), LocalDate.of(2020,4,28), 2, types, 55000);
        types = new String[]{"single", "double"};
        Hotel hotel6 = new Hotel("Grand Hotel Saint Michel", "París", LocalDate.now(), LocalDate.of(2020,4,28), 1, types, 34000);
        //Hotel hotel7 = new Hotel("Grand Hotel Saint Michel", "París", LocalDate.now(), LocalDate.of(2020,4,25), 2, types);
        Hotel hotel8 = new Hotel("Absalon Hotel", "Copenhagen", LocalDate.now(), LocalDate.of(2020,4,28), 1, types, 64000);
        Hotel hotel9 = new Hotel("Copenhagen Marriott Hotel", "Copenhagen", LocalDate.now(), LocalDate.of(2020,4,28), 2, types, 45000);
        Hotel hotel10 = new Hotel("Lux Eleven Berlin-Mitte", "London", LocalDate.now(), LocalDate.of(2020,4,28), 1, types, 47000);

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
     * Getter sem skilar öllum gagnagrunni af hótelum.
     *
     * @return - ArrayList<Hotel> - listi með gagnagrunninum af hótelum
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