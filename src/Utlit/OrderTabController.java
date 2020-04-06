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

import java.util.Collections;


public class OrderTabController {
    @FXML
    private ListView<Flight> ordersListViewFlights;
    @FXML
    private ListView<Hotel> ordersListViewHotels;
    @FXML
    private ListView<DayTrip> ordersListViewDayTrips;
    @FXML
    private ListView<Package> ordersListViewPackages;
    @FXML
    private Label verdLabel;

    public void setjaInnFlights(ObservableList<Flight> flights) {
        ordersListViewFlights.setItems(flights);
        reiknaVerd();
    }

    public void setjaInnHotel(ObservableList<Hotel> hotel){
        ordersListViewHotels.setItems(hotel);
        reiknaVerd();
    }

    public void setjaInnDayTrips(ObservableList<DayTrip> dayTrip){
        ordersListViewDayTrips.setItems(dayTrip);
    }


    public void setjaInnPackages(ObservableList<Package> packages){
        ordersListViewPackages.setItems(packages);
        reiknaVerd();
    }


    /**
     * Reiknar verð allra pantaðra hluta og sýnir verðið.
     *
     */
    private void reiknaVerd(){

        int verd = 0;
        ObservableList<Hotel> theHotels = ordersListViewHotels.getItems();
        for(Hotel h: theHotels){
            verd += h.getPrice();
        }
        ObservableList<Flight> theFlights = ordersListViewFlights.getItems();
        for (Flight flight : theFlights) {
            verd += flight.getPrice();
        }
        ObservableList<Package> thePackages= ordersListViewPackages.getItems();
        for (Package packages : thePackages){
            verd += packages.getPrice();
        }
        verdLabel.setText(verd +" -kr.");
    }

    /**
     * Raðar lista með pöntuðum flugum eftir verði.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á raða eftir verði takka
     */
    @FXML
    private void radaFlightsHandler(ActionEvent actionEvent) {
        ObservableList<Flight> theFlights = ordersListViewFlights.getItems();
        Collections.sort(theFlights);
        ordersListViewFlights.setItems(theFlights);
    }

    public void radaHotelsHandler(ActionEvent actionEvent) {
        ObservableList<Hotel> theHotels = ordersListViewHotels.getItems();
        Collections.sort(theHotels);
        ordersListViewHotels.setItems(theHotels);
    }

    public void radaDayTripsHandler(ActionEvent actionEvent) {

    }

    public void radaPackagesHandler(ActionEvent actionEvent) {

    }
}
