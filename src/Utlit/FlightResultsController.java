package Utlit;

import Vinnsla.Flight;
import Vinnsla.FlightList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class FlightResultsController implements Initializable {

    @FXML
    private AnchorPane flightResultsWindow;     // Viðmótsgluggi fyrir niðurstöður
    @FXML
    private ListView<Flight> flightsList;       // Listi til að birta flug

    @FXML
    FlightTabController flightTabController;

    private FlightList flightsGogn;             // Tenging við gögn í lista af flugum
    private int virkurIndex = 0;                // Geymir hvaða flug í lista er valið

    /**
     * Upphafsstillir lista af flugum og setur í ListView hlut.
     * Heldur utan um hvaða stak í lista er valið.
     * Setur change listener á ListView hlutinn.
     *
     */
    public void initialize(URL location, ResourceBundle resource) {
        flightsGogn = new FlightList();
        flightsList.setItems(flightsGogn.getAllFlights());
        MultipleSelectionModel<Flight> lsr = flightsList.getSelectionModel();
        lsr.selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
                virkurIndex = lsr.getSelectedIndex();
            }
        });
    }

    public FlightList getFlightsGogn() {
        return flightsGogn;
    }

    public int getVirkurIndex() {
        return virkurIndex;
    }

    public void searchFlightsOneWay(String depart, String dest, LocalDate date, int noOfPeople) {
        flightsGogn.searchFlightsOneWay(depart, dest, date, noOfPeople);
        showResults();
    }

    public void searchFlightsRoundWay(String depart, String dest, LocalDate from, LocalDate to, int noOfPeople) {
        flightsGogn.searchFlightsRoundWay(depart, dest, from, to, noOfPeople);
        showResults();
    }

    private void showResults() {
        DialogPane p = new DialogPane();
        flightResultsWindow.setVisible(true);

        p.setContent(flightResultsWindow);

        Dialog d = new Dialog();

        d.setDialogPane(p);
        d.setHeaderText("Flug sem passa við leit");
        d.setTitle("Leitarniðurstöður");
    }
}
