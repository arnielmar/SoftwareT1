package Utlit;

import Vinnsla.DayTrip;
import Vinnsla.Flight;
import Vinnsla.Hotel;
import Vinnsla.Package;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

public class OrderTabController {
    @FXML
    private ListView<Flight> ordersListViewFlights;
    @FXML
    private ListView<Hotel> ordersListViewHotels;
    @FXML
    private ListView<DayTrip> ordersListViewDayTrips;
    @FXML
    private ListView<Package> ordersListViewPackages;

    public void setjaInnFlights(ObservableList<Flight> flights) {
        ordersListViewFlights.setItems(flights);
    }

    public void setjaInnHotel(ObservableList<Hotel> hotel){
        ordersListViewHotels.setItems(hotel);
    }

    public void setjaInnDayTrips(ObservableList<DayTrip> dayTrip){
        ordersListViewDayTrips.setItems(dayTrip);
    }
}
