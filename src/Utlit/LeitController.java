package Utlit;

import Vinnsla.DayTrip;
import Vinnsla.Flight;
import Vinnsla.Hotel;
import Vinnsla.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;

public class LeitController {
    @FXML
    private FlightTabController flightTabController;
    @FXML
    private HotelTabController hotelTabController;
    @FXML
    private DayTripTabController dayTripTabController;
    @FXML
    private PackageTabController packageTabController;
    @FXML
    private OrderTabController orderTabController;
    @FXML
    private Tab flightTab;
    @FXML
    private Tab hotelTab;
    @FXML
    private Tab dayTripTab;
    @FXML
    private Tab packageTab;
    @FXML
    private Tab orderTab;

    /**
     * Tengir alla controllera við LeitController.
     *
     */
    public void initialize() {
        flightTabController.setParentController(this);
        hotelTabController.setParentController(this);
        dayTripTabController.setParentController(this);
        packageTabController.setParentController(this);
        orderTabController.setParentController(this);
    }

    /**
     * Milliliður fyrir að panta flug.
     *
     * @param flights - listi með pöntuðum flugum sem á að birta í OrderTabController
     */
    public void setjaFlights(ObservableList<Flight> flights) {
        orderTabController.setjaInnFlights(flights);
    }

    /**
     * Milliliður fyrir að panta hótel.
     *
     * @param hotel - listi með pöntuðum hótelum sem á að birta í OrderTabController
     */
    public void setjaHotel(ObservableList<Hotel> hotel){
        orderTabController.setjaInnHotel(hotel);
    }

    /**
     * Milliliður fyrir að panta day trips.
     *
     * @param dayTrips - listi með pöntuðum day trips sem á að birta í OrderTabController
     */
    public void setjaDayTrips(ObservableList<DayTrip> dayTrips)
    {
        orderTabController.setjaInnDayTrips(dayTrips);
    }

    /**
     * Milliliður fyrir að panta pakka.
     *
     * @param packages - listi með pöntuðum pökkum sem á að birta í OrderTabController
     */
    public void setjaPackage(ObservableList<Package> packages)
    {
        orderTabController.setjaInnPackages(packages);
    }

    /**
     * Tæmir alla lista með pöntuðum hlutum og lætur OrderTabController
     * sýna þessa tómu lista.
     *
     */
    public void endurstillaLista() {
        ArrayList<Flight> nyrFlight = flightTabController.removeAllOrderedFlights();
        setjaFlights(FXCollections.observableArrayList(nyrFlight));
        ObservableList<Hotel> nyrHotel = hotelTabController.removeAllOrderedHotels();
        setjaHotel(nyrHotel);
        ArrayList<DayTrip> nyrDayTrip = dayTripTabController.removeAllOrderedDayTrips();
        setjaDayTrips(FXCollections.observableArrayList(nyrDayTrip));
        ObservableList<Package> nyrPackage = packageTabController.removeAllOrderedPackages();
        setjaPackage(nyrPackage);
    }

    /**
     * Kallar á aðferð í FlightTabController til að eyða
     * pöntuðum flughlut.
     *
     * @param flight - Flight hlutur sem á að eyða
     */
    public void deleteFlight(Flight flight) {
        flightTabController.removeOrderedFlight(flight);
    }

    /**
     * Kallar á aðferð í HotelTabController til að eyða
     * pöntuðum hótelhlut.
     *
     * @param hotel - Hotel hlutur sem á að eyða
     */
    public void deleteHotel(Hotel hotel) {
        hotelTabController.removeOrderedHotel(hotel);
    }
}
