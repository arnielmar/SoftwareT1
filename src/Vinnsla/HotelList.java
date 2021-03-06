package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Geymir lista af hótelum, pöntuðum hótelum og pöntuðum hótelherbergjum
 * og hefur aðferð til að leita að hótelum í gagnagrunni.
 *
 */
public class HotelList {
    private ObservableList<Hotel> hotels;
    private ArrayList<Hotel> orderedHotels;
    private ArrayList<String> orderedRoomTypes;

    /**
     * Smíðar nýjan Observable List af Hotel hlutum,
     * ArrayList af Hotel hlutum og ArrayList af String hlutum.
     *
     */
    public HotelList() {
        this.hotels = FXCollections.observableArrayList();
        this.orderedHotels = new ArrayList<Hotel>();
        this.orderedRoomTypes = new ArrayList<String>();
    }

    /**
     * Skilar hóteli sem er númer index á lista.
     *
     * @param index - nr. hótels
     * @return Flight - flug nr. index í lista
     */
    public Hotel getHotel(int index) {
        return (Hotel)hotels.get(index);
    }

    /**
     * Skilar öllum lista af hótelum.
     *
     * @return - lista af Hotel hlutum
     */
    public ObservableList<Hotel> getAllHotels() {
        return hotels;
    }

    /**
     * Eyðir hóteli nr. index úr lista.
     *
     * @param index - nr. hótels
     */
    public void deleteHotel(int index) {
        hotels.remove(index);
    }

    /**
     * Bætir hóteli flight við á lista.
     *
     * @param hotel - Hotel hlutur sem bæta á við í lista
     */
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    /**
     * Leitar að hótelum á staðsetningu dest frá dagsetningu from
     * til dagsetningu to fyrir noOfPeople manns eftir tegund herbergis.
     * Skilar ArrayList<H1>.
     *
     * @param dest - Áfangastaður
     * @param from - Dagsetning komu
     * @param to - Dagsetning brottfararar
     * @param noOfPeople - Fjöldi manns
     */
    public static ArrayList<Hotel> searchHotels(String dest, LocalDate from, LocalDate to, int noOfPeople) {
        ArrayList<Hotel> allHotels = ListOfHotels.getListOfHotels();
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        for(Hotel h :allHotels){
            if(h.getLocation().equals(dest) &&
                    h.getArrivalDate().equals(from) &&
                    h.getDepartDate().equals(to) &&
                    h.getCustomerNumber() >= noOfPeople){
                results.add(h);
            }
        }
        return results;
    }

    /**
     * Bætir hóteli við lista með pöntuðum hótelum.
     *
     * @param h - Hotel hlutur sem bæta skal við
     * @param s - Tegund hótelherbergis
     */
    public void addOrderedHotel(Hotel h, String s){
        orderedHotels.add(h);
        orderedRoomTypes.add(s);
    }

    /**
     * Skilar lista með öllum pöntuðum hótelum.
     *
     * @return - lista af pöntuðum hótelum
     */
    public ObservableList<Hotel> getOrderedHotels(){
        return FXCollections.observableArrayList(orderedHotels);
    }

    /**
     * Eyðir öllum pöntuðum hótelum úr lista.
     *
     */
    public void removeAllOrderedHotels() {
        orderedHotels.clear();
    }

    /**
     * Skilar lista með öllum tegundum pantaðra hótelherbergja.
     *
     * @return - lista af pöntuðum hótelherbergjum
     */
    public ObservableList<String> getOrderedRoomTypes(){
        return FXCollections.observableArrayList(orderedRoomTypes);
    }

    /**
     * Eyðir hótelhlut úr lista með pöntuðum hótelum.
     *
     * @param hotel - Hotel hlutur sem skal eyða.
     */
    public void removeOrderedHotel(Hotel hotel) {
        orderedHotels.remove(hotel);
    }
}
