package Utlit;

import Vinnsla.DayTrip;
import Vinnsla.Flight;
import Vinnsla.Hotel;
import Vinnsla.Package;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Button haldaAframButton;
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
    private Label heimilisfangLabel;
    @FXML
    private Label kortLabel;
    @FXML
    private TextField heimilisfangTextField;
    @FXML
    private TextField kortTextField;
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

    public void initialize() {
        stadfestaButtonOvirkurRegla();
        nafnTextFieldRegla();
        kennitalaTextFieldRegla();
        kortTextFieldRegla();
    }

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
    private void tilBakaHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        virkjaPantanir(true);
    }

    @FXML
    private void stadfestaHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        pontunKlarudLabel.setText("Takk fyrir! Pöntun þín hefur verið móttekin.");
        pontunKlarudLabel.setVisible(true);
        skodaPontunButton.setVisible(true);
        haldaAframButton.setVisible(true);
    }

    @FXML
    private void skodaPontunHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        virkjaPantanir(true);
        pontunKlarudLabel.setVisible(false);
        skodaPontunButton.setVisible(false);
        stadfestaButton.setVisible(false);
        klaraPontunButton.setVisible(false);
        haldaAframButton.setVisible(false);
        pontunButton.setVisible(true);
    }

    @FXML
    private void tilBakaPontunHandler(ActionEvent actionEvent) {
        virkjaPantanir(false);
        pontunKlarudLabel.setVisible(true);
        skodaPontunButton.setVisible(true);
        haldaAframButton.setVisible(true);
        pontunButton.setVisible(false);
    }

    @FXML
    private void haldaAframHandler(ActionEvent actionEvent) {
        parentController.endurstillaLista();
        virkjaCheckout(false);
        pontunKlarudLabel.setVisible(false);
        skodaPontunButton.setVisible(false);
        haldaAframButton.setVisible(false);
        virkjaPantanir(true);
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
        heimilisfangLabel.setVisible(gildi);
        kortLabel.setVisible(gildi);
        nafnTextField.setVisible(gildi);
        kennitalaTextField.setVisible(gildi);
        heimilisfangTextField.setVisible(gildi);
        kortTextField.setVisible(gildi);
        stadfestaButton.setVisible(gildi);
        tilBakaButton.setVisible(gildi);
    }

    private void stadfestaButtonOvirkurRegla() {
        stadfestaButton.disableProperty()
                .bind(nafnTextField.textProperty().isEmpty()
                        .or(kennitalaTextField.textProperty().isEmpty())
                            .or(heimilisfangTextField.textProperty().isEmpty())
                                .or(kortTextField.textProperty().isEmpty()));
    }

    private void nafnTextFieldRegla() {
        nafnTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                nafnTextField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    private void kennitalaTextFieldRegla() {
        kennitalaTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    kennitalaTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void kortTextFieldRegla() {
        kortTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    kortTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
