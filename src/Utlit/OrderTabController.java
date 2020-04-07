package Utlit;

import Vinnsla.DayTrip;
import Vinnsla.Flight;
import Vinnsla.Hotel;
import Vinnsla.Package;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Collections;


public class OrderTabController {
    // viðmótshlutir fyrir checkout
    @FXML
    private Button pontunButton;
    @FXML
    private Button tilBakaButton;
    @FXML
    private Label pontunKlarudLabel;
    @FXML
    private Button stadfestaButton;
    @FXML
    private Label nafnLabel;
    @FXML
    private Label kennitalaLabel;
    @FXML
    private TextField nafnTextField;
    @FXML
    private TextField kennitalaTextField;
    @FXML
    private Label checkoutLabel;
    @FXML
    private Button skodaPontunButton;
    // viðmótshlutir sem sýna pantanir
    @FXML
    private Label orderLabel;
    @FXML
    private Button radaFlights;
    @FXML
    private Button radaHotels;
    @FXML
    private Button radaDayTrips;
    @FXML
    private Button radaPackages;
    @FXML
    private Button klaraPontunButton;
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
    @FXML
    private Label flightsLabel;
    @FXML
    private Label hotelsLabel;
    @FXML
    private Label dayTripsLabel;
    @FXML
    private Label packagesLabel;

    private LeitController parentController;    // tenging við LeitController

    public void setParentController(LeitController parentController) {
        this.parentController = parentController;
    }

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

    @FXML
    private void radaHotelsHandler(ActionEvent actionEvent) {
        ObservableList<Hotel> theHotels = ordersListViewHotels.getItems();
        Collections.sort(theHotels);
        ordersListViewHotels.setItems(theHotels);
    }

    @FXML
    private void radaDayTripsHandler(ActionEvent actionEvent) {
    }

    @FXML
    private void radaPackagesHandler(ActionEvent actionEvent) {
    }

    @FXML
    private void klaraPontunHandler(ActionEvent actionEvent) {
        virkjaPantanir(false);
        virkjaCheckout(true);
    }

    @FXML
    private void stadfestaHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        String nafn = nafnTextField.getText();
        String kt = kennitalaTextField.getText();
        pontunKlarudLabel.setText("Takk fyrir " + nafn + ", " + kt + "\nPöntun þín hefur verið móttekin.");
        pontunKlarudLabel.setVisible(true);
        skodaPontunButton.setVisible(true);
    }

    @FXML
    private void tilBakaHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        virkjaPantanir(true);
    }

    @FXML
    private void skodaPontunHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        virkjaPantanir(true);
        pontunKlarudLabel.setVisible(false);
        skodaPontunButton.setVisible(false);
        stadfestaButton.setVisible(false);
        klaraPontunButton.setVisible(false);
        pontunButton.setVisible(true);
    }

    @FXML
    private void tilBakaPontunHandler(ActionEvent actionEvent) {
        virkjaPantanir(false);
        pontunKlarudLabel.setVisible(true);
        skodaPontunButton.setVisible(true);
        pontunButton.setVisible(false);
    }

    private void virkjaPantanir(boolean gildi) {
        flightsLabel.setVisible(gildi);
        hotelsLabel.setVisible(gildi);
        dayTripsLabel.setVisible(gildi);
        packagesLabel.setVisible(gildi);
        orderLabel.setVisible(gildi);
        ordersListViewFlights.setVisible(gildi);
        ordersListViewHotels.setVisible(gildi);
        ordersListViewDayTrips.setVisible(gildi);
        ordersListViewPackages.setVisible(gildi);
        verdLabel.setVisible(gildi);
        klaraPontunButton.setVisible(gildi);
        radaFlights.setVisible(gildi);
        radaHotels.setVisible(gildi);
        radaDayTrips.setVisible(gildi);
        radaPackages.setVisible(gildi);
    }

    private void virkjaCheckout(boolean gildi) {
        checkoutLabel.setVisible(gildi);
        nafnLabel.setVisible(gildi);
        kennitalaLabel.setVisible(gildi);
        nafnTextField.setVisible(gildi);
        kennitalaTextField.setVisible(gildi);
        stadfestaButton.setVisible(gildi);
        tilBakaButton.setVisible(gildi);
    }
}
