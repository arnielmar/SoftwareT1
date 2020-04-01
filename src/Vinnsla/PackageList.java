package Vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Integer.min;

public class PackageList {
    private ObservableList<Package> packages;

    /**
     * Smíðar nýjan Observable List af Package hlutum.
     */
    public PackageList() {
        this.packages = FXCollections.observableArrayList();
    }

    /**
     * Skilar pakka sem er númer index á lista.
     *
     * @param index - nr. pakka
     * @return Package - pakki nr. index í lista
     */
    public Package getPackage(int index) {
        return (Package)packages.get(index);
    }

    /**
     * Skilar öllum lista af pökkum.
     *
     * @return - lista af Package hlutum
     */
    public ObservableList<Package> getAllPackages() {
        return packages;
    }

    /**
     * Eyðir pakka nr. index úr lista.
     *
     * @param index - nr. pakka
     */
    public void deletePackage(int index) {
        packages.remove(index);
    }

    /**
     * Bætir pakka package við á lista.
     *
     * @param pack - Package hlutur sem bæta á við í lista
     */
    public void addPackage(Package pack) {
        packages.add(pack);
    }

    /**
     * Leitar að pökkum á staðsetningu dest frá staðsetningu depart
     * frá dagsetningu from til dagsetningu to fyrir noOfPeople manns.
     * Skilar ArrayList<Package>.
     *
     * @param depart - Brottfararstaður
     * @param dest - Áfangastaður
     * @param from - Dagsetning komu
     * @param to - Dagsetning brottfararar
     * @param noOfPeople - Fjöldi manns
     */
    public void searchPackages(String depart, String dest, LocalDate from, LocalDate to, int noOfPeople) {

        //Passa að allt uppfyllist

        // Byrja á því að leita af listum fyrir flug frá og til, daytrip og Hótel.
        FlightList Flug= new FlightList();
        Flug.searchFlightsRoundWay(dest,depart,from,to,noOfPeople);
        HotelList Hotl= new HotelList();
        // Hotl.searchHotels(dest,from,to,noOfPeople, "Hvað með Tegund?");
        DayTripList Dagferd = new DayTripList();
        // Dagferd.searchHotels(dest,from,"hvernig ætti maður að finna lengd",noOfPeople, " hvað með Tegund?");

        // hér er svo sett up lengdina af öllum þremur listum og minnsta af þeim
        int Flugfjoldi = Flug.getAllFlights().size();
        int Hotelfjoldi = Hotl.getAllHotels().size();
        int Dagferdfjoldi = Dagferd.getAllDayTrips().size();
        int Minfjoldi = min(Flugfjoldi,min(Hotelfjoldi,min(Dagferdfjoldi,5)));

        // Hér er svo sett up pakkar til að sýna.
        for (int i = 0; i < Minfjoldi; i++) {
            int Fr = new java.util.Random().nextInt(Flugfjoldi);
            int Hr = new java.util.Random().nextInt(Hotelfjoldi);
            int Dr = new java.util.Random().nextInt(Dagferdfjoldi);
            // Vantar að vita hvernig ég les úr searchflightroundway fyrir fluginn hér að neðan
            Package P = new Package(Flug.getFlight(Fr),Flug.getFlight(Fr),Hotl.getHotel(Hr),Dagferd.getDayTrip(Dr));
            packages.add(P);
        }



                



        //TODO
    }
}
