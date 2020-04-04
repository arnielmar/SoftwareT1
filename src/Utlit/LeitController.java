package Utlit;

import Vinnsla.Hotel;
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
        hotelTabController.setParentController(this);
    }
    public void setjaHotel(ObservableList<Hotel> hotel){
        orderTabController.setjaInnHotel(hotel);
    }
}
