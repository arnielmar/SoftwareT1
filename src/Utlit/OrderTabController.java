package Utlit;

import Vinnsla.DayTrip;
import Vinnsla.Flight;
import Vinnsla.Hotel;
import Vinnsla.Package;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OrderTabController {
    @FXML
    private ListView<Flight> ordersListViewFlights;
    @FXML
    private ListView<Hotel> ordersListViewHotels;
    @FXML
    private ListView<DayTrip> ordersListViewDayTrips;
    @FXML
    private ListView<Package> ordersListViewPackages;

    public void initialize() {
        setjaLista();
    }

    private void setjaLista() {
    }
}
