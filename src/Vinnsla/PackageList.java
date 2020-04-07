package Vinnsla;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Integer.min;

public class PackageList {
    private ObservableList<Package> packages;
    private ArrayList<Package> orderedPackages;

    /**
     * Smíðar nýjan Observable List af Package hlutum.
     */
    public PackageList() {
        this.packages = FXCollections.observableArrayList();
        this.orderedPackages= new ArrayList<Package>();
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
     * @param p - pakkin sem á að fjarlægja
     */
    public void deletePackage(Package p) {
        packages.remove(p);
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
        //FlightList Flug= new FlightList();
        //Flug.searchFlightsRoundWay(dest,depart,from,to,noOfPeople);


        FlightList flightList= new FlightList();
        ArrayList<Flight> fromResults = flightList.searchFlights(depart, dest, from, noOfPeople);
        ArrayList<Flight> toResults = flightList.searchFlights(dest, depart, to, noOfPeople);
        HotelList Hotl= new HotelList();
        ArrayList<Hotel> hotelz=  Hotl.searchHotels(dest,from,to,noOfPeople);
        DayTripList Dagferd = new DayTripList();
        ArrayList<DayTrip> dagferd = new ArrayList<DayTrip>();
        for (LocalDate date= from; date.isBefore(to); date=date.plusDays(1)){
            ArrayList<DayTrip> dagtemp = Dagferd.searchDayTrips(dest,date,noOfPeople);
            dagferd.addAll(dagtemp);
        }
        //ArrayList<DayTrip> dagferd = Dagferd.searchDayTrips(dest,from,noOfPeople);

        // hér er svo sett up lengdina af öllum þremur listum og minnsta af þeim
        int Flugfjoldifrom = fromResults.size();
        int Flugfjoldito = toResults.size();
        int Hotelfjoldi = hotelz.size();
        int Dagferdfjoldi = dagferd.size();
        int Minfjoldi = min(Flugfjoldifrom,min(Flugfjoldito,min(Hotelfjoldi,min(Dagferdfjoldi,5))));

        // Hér er svo sett up pakkar til að sýna.
        for (int i = 0; i < Minfjoldi; i++) {
            int fr1 = new java.util.Random().nextInt(Flugfjoldifrom);
            int fr2 = new java.util.Random().nextInt(Flugfjoldito);
            int Hr = new java.util.Random().nextInt(Hotelfjoldi);
            int Dr = new java.util.Random().nextInt(Dagferdfjoldi);
            Package P = new Package(fromResults.get(fr1),toResults.get(fr2),hotelz.get(Hr),dagferd.get(Dr));
            packages.add(P);
        }
        //TODO
    }

    public void addOrderedPackage(Package p){
        orderedPackages.add(p);
    }

    public void removeAllOrderedPackages() {
        orderedPackages.clear();
    }

    public ObservableList<Package> getOrderedPackages() { return FXCollections.observableArrayList(orderedPackages);}
}
