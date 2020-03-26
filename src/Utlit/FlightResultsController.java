package Utlit;

import Vinnsla.Flight;
import Vinnsla.FlightList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.AnchorPane;


public class FlightResultsController {

    @FXML
    private AnchorPane flightResultsWindow;
    @FXML
    private ListView<Flight> flightsList;

    private FlightList flightsGogn;
    private int virkurIndex = 0;

    /**
     * Upphafsstillir lista af flugum og setur í ListView hlut.
     * Setur change listener á ListView hlutinn.
     *
     */
    public void initialize() {
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

    public void showResults() {
        DialogPane p = new DialogPane();
        flightResultsWindow.setVisible(true);

        p.setContent(flightResultsWindow);
    }
}
