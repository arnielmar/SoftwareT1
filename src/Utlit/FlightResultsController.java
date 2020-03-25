package Utlit;

import Vinnsla.Flight;
import Vinnsla.FlightList;
import Vinnsla.mockupFlight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightResultsController implements Initializable {

    @FXML
    private AnchorPane flightResultsWindow;
    @FXML
    private ListView<mockupFlight> flightsList;

    private FlightList flightsGogn;
    private int virkurIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flightsGogn = new FlightList();
        flightsList.setItems(flightsGogn.getAllFlights());
        MultipleSelectionModel<mockupFlight> lsr = flightsList.getSelectionModel();
        lsr.selectedItemProperty().addListener(new ChangeListener<mockupFlight>() {
            @Override
            public void changed(ObservableValue<? extends mockupFlight> observable, mockupFlight oldValue, mockupFlight newValue) {
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
