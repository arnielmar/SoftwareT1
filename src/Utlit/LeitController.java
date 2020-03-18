package Utlit;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LeitController implements Initializable {
    @FXML
    private Tab flightTab;
    @FXML
    private Tab hotelTab;
    @FXML
    private Tab dayTripTab;
    @FXML
    private Tab packageTab;

    @FXML
    private FlightTabController flightTabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
