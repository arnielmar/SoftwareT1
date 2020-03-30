package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelList {
    private ObservableList<Hotel> hotels;

    /**
     * Smíðar nýjan Observable List af Hotel hlutum.
     */
    public HotelList() {
        this.hotels = FXCollections.observableArrayList();
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
     * @param tegund - Tegund herbergis
     */
    public void searchHotels(String dest, LocalDate from, LocalDate to, int noOfPeople, String tegund) {
        //TODO
    }
}
