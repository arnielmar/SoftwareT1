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

/**
 * Controller fyrir Order tab.
 *
 */
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
    @FXML
    private Button removeFlights;
    @FXML
    private Button removeHotels;
    @FXML
    private Button removeDayTrips;
    @FXML
    private Button removePackages;

    private LeitController parentController;    // tenging við LeitController

    /**
     * Upphafsstillir gluggann fyrir pantanir.
     * Setur reglur á staðfesta takka og á textasviðin.
     *
     */
    public void initialize() {
        stadfestaButtonOvirkurRegla();
        nafnTextFieldRegla();
        kennitalaTextFieldRegla();
        kortTextFieldRegla();
    }

    /**
     * Tenging við aðal controller-inn.
     *
     * @param parentController - LeitController
     */
    public void setParentController(LeitController parentController) {
        this.parentController = parentController;
    }

    /**
     * Setur lista með flugum í list view.
     *
     * @param flights - listi af flugum
     */
    public void setjaInnFlights(ObservableList<Flight> flights) {
        ordersListViewFlights.setItems(flights);
        reiknaVerd();
    }

    /**
     * Setur lista með hótelum í list view.
     *
     * @param hotel - listi af hótelum
     */
    public void setjaInnHotel(ObservableList<Hotel> hotel){
        ordersListViewHotels.setItems(hotel);
        reiknaVerd();
    }

    /**
     * Setur lista með day trips í list view.
     *
     * @param dayTrip - listi af day trips
     */
    public void setjaInnDayTrips(ObservableList<DayTrip> dayTrip){
        ordersListViewDayTrips.setItems(dayTrip);
        reiknaVerd();
    }

    /**
     * Setur lista af pökkum í list view.
     *
     * @param packages - listi af pökkum
     */
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

    /**
     * Raðar lista með pöntuðum hótelum eftir verði.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á raða eftir verði takka
     */
    @FXML
    private void radaHotelsHandler(ActionEvent actionEvent) {
        ObservableList<Hotel> theHotels = ordersListViewHotels.getItems();
        Collections.sort(theHotels);
        ordersListViewHotels.setItems(theHotels);
    }

    /**
     * Raðar lista með pöntuðum day trips eftir verði.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á raða eftir verði takka
     */
    @FXML
    private void radaDayTripsHandler(ActionEvent actionEvent) {
        ObservableList<DayTrip> theDayTrips = ordersListViewDayTrips.getItems();
        Collections.sort(theDayTrips);
        ordersListViewDayTrips.setItems(theDayTrips);
    }

    /**
     * Raðar lista með pöntuðum pökkum eftir verði.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á raða eftir verði takka
     */
    @FXML
    private void radaPackagesHandler(ActionEvent actionEvent) {
        ObservableList<Package> thePackages = ordersListViewPackages.getItems();
        Collections.sort(thePackages);
        ordersListViewPackages.setItems(thePackages);
    }

    /**
     * Fer yfir í viðmótsglugga fyrir checkout.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á klára pöntun takka
     */
    @FXML
    private void klaraPontunHandler(ActionEvent actionEvent) {
        virkjaPantanir(false);
        virkjaCheckout(true);
    }

    /**
     * Fer yfir í viðmótsglugga fyrir pantanir.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á til baka takka
     */
    @FXML
    private void tilBakaHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        virkjaPantanir(true);
    }

    /**
     * Sýnir staðfestingarskilaboð á pöntun.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á staðfesta pöntun takka
     */
    @FXML
    private void stadfestaHandler(ActionEvent actionEvent) {
        virkjaCheckout(false);
        pontunKlarudLabel.setText("Thank you! Your order has been received.");
        pontunKlarudLabel.setVisible(true);
        skodaPontunButton.setVisible(true);
        haldaAframButton.setVisible(true);
    }

    /**
     * Sýnir viðmótsglugga til að skoða pantanir sem hafa verið staðfestar.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á skoða pöntun takka
     */
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
        removeFlights.setVisible(false);
        removeHotels.setVisible(false);
        removeDayTrips.setVisible(false);
        removePackages.setVisible(false);
    }

    /**
     * Sýnir staðfestingarglugga á pöntun.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á til baka í pöntun takka
     */
    @FXML
    private void tilBakaPontunHandler(ActionEvent actionEvent) {
        virkjaPantanir(false);
        pontunKlarudLabel.setVisible(true);
        skodaPontunButton.setVisible(true);
        haldaAframButton.setVisible(true);
        pontunButton.setVisible(false);
    }

    /**
     * Leyfir notanda að halda áfram að versla.
     * Eyðir út fyrri pöntun úr listum.
     *
     * @param actionEvent - atburðurinn þegar klikkað er á halda áfram að versla takka
     */
    @FXML
    private void haldaAframHandler(ActionEvent actionEvent) {
        parentController.endurstillaLista();
        virkjaCheckout(false);
        pontunKlarudLabel.setVisible(false);
        skodaPontunButton.setVisible(false);
        haldaAframButton.setVisible(false);
        virkjaPantanir(true);
    }

    /**
     * Gerir viðmótshluti fyrir pantanir sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     *
     * @param gildi - true ef á að virkja, annars false
     */
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
        removeFlights.setVisible(gildi);
        removeHotels.setVisible(gildi);
        removeDayTrips.setVisible(gildi);
        removePackages.setVisible(gildi);
    }

    /**
     * Gerir viðmótshluti fyrir checkout sýnilega ef gildi er true,
     * en felur viðmótshluti annars.
     *
     * @param gildi - true ef á að virkja, annars false
     */
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

    /**
     * Setur reglu á staðfesta pöntun takka.
     * Ef eitthvert af textasviðunum er tómt þá er hann óvirkur.
     *
     */
    private void stadfestaButtonOvirkurRegla() {
        stadfestaButton.disableProperty()
                .bind(nafnTextField.textProperty().isEmpty()
                        .or(kennitalaTextField.textProperty().isEmpty())
                            .or(heimilisfangTextField.textProperty().isEmpty())
                                .or(kortTextField.textProperty().isEmpty()));
    }

    /**
     * Setur reglu á textasvið fyrir nafn sem leyfir aðeins
     * stafi úr enska stafrófinu.
     *
     */
    private void nafnTextFieldRegla() {
        nafnTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                nafnTextField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    /**
     * Setur reglu á textasvið fyrir kennitölu sem leyfir
     * aðeins tölustafi.
     *
     */
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

    /**
     * Setur reglu á textasvið fyrir kortanúmer sem
     * leyfir aðeins tölustafi.
     *
     */
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

    /**
     * Eyðir flughlut úr pöntuðum flugum.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á remove
     */
    public void removeFlightsHandler(ActionEvent actionEvent) {
        Flight flug = ordersListViewFlights.getSelectionModel().getSelectedItem();
        ObservableList<Flight> listi = ordersListViewFlights.getItems();
        listi.remove(flug);
        setjaInnFlights(listi);
        parentController.deleteFlight(flug);
    }

    /**
     * Eyðir hótelhlut úr pöntuðum hótelum.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á remove
     */
    public void removeHotelsHandler(ActionEvent actionEvent) {
        Hotel hotel = ordersListViewHotels.getSelectionModel().getSelectedItem();
        ObservableList<Hotel> listi = ordersListViewHotels.getItems();
        listi.remove(hotel);
        setjaInnHotel(listi);
        parentController.deleteHotel(hotel);
    }

    public void removeDayTripsHandler(ActionEvent actionEvent) {
        DayTrip dt = ordersListViewDayTrips.getSelectionModel().getSelectedItem();
        ObservableList<DayTrip> listi = ordersListViewDayTrips.getItems();
        listi.remove(dt);
        setjaInnDayTrips(listi);
        parentController.deleteDayTrip(dt);
    }

    /**
     * Eyðir packagehlut úr pöntuðum pökkum.
     *
     * @param actionEvent - Atburðurinn þegar klikkað er á remove
     */
    public void removePackagesHandler(ActionEvent actionEvent) {
        Package packages = ordersListViewPackages.getSelectionModel().getSelectedItem();
        ObservableList<Package> listi = ordersListViewPackages.getItems();
        listi.remove(packages);
        setjaInnPackages(listi);
        parentController.deletePackages(packages);
    }
}
