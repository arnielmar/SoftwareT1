package Utlit;

import Vinnsla.DayTrip;
import Vinnsla.Flight;
import Vinnsla.Hotel;
import Vinnsla.Package;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

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


    public void initialize() {
        flightTabController.setParentController(this);
        hotelTabController.setParentController(this);
        dayTripTabController.setParentController(this);
        packageTabController.setParentController(this);
    }

    public void setjaFlights(ObservableList<Flight> flights) {
        orderTabController.setjaInnFlights(flights);
    }

    public void setjaHotel(ObservableList<Hotel> hotel){
        orderTabController.setjaInnHotel(hotel);
    }

    public void setjaDayTrips(ObservableList<DayTrip> dayTrips)
    {
        orderTabController.setjaInnDayTrips(dayTrips);
    }

    public void setjaPackage(ObservableList<Package> packages)
    {
        orderTabController.setjaInnPackages(packages);
    }
}
